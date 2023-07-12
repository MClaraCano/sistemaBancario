package com.conspring.banco.application.services;

import com.conspring.banco.api.dtos.TransferDto;
import com.conspring.banco.api.mappers.TransferMapper;
import com.conspring.banco.domain.exceptions.NoSeEncontroE;
import com.conspring.banco.application.exceptions.SaldoInsuficiente;
import com.conspring.banco.domain.models.Account;
import com.conspring.banco.domain.models.Transfer;
import com.conspring.banco.infrastructure.repositories.AccountRepository;
import com.conspring.banco.infrastructure.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;
    private AccountRepository accountRepository;
    private AccountService accountService;

    public List<TransferDto> obtenerTransferencias() {
        List<Transfer> listaTransfers = transferRepository.findAll();
        List<TransferDto> listaDto = listaTransfers.stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
        return listaDto;
    }

    public Transfer obtenerId(Long id) throws NoSeEncontroE {
        Transfer transfer = transferRepository.findById(id).orElse(null);
        if (transfer == null){
            throw new NoSeEncontroE("No existe el ID " + id);
        }
        return transfer;
    }


    public TransferDto getTransferById(Long id) throws NoSeEncontroE {
        Transfer transfer = obtenerId(id);
        TransferDto transferDto = TransferMapper.transferToDto(transfer);
        return transferDto;
    }

    public TransferDto crearTransfer(TransferDto transferDto){
        Transfer transfer = transferRepository.save(TransferMapper.dtoToTransfer(transferDto));
        transferDto = TransferMapper.transferToDto(transfer);
        return transferDto;
    }

    public TransferDto modificarTransfer(TransferDto transferDto) throws NoSeEncontroE {

        if (obtenerId(transferDto.getId()) != null){
            Transfer transfer = transferRepository.save(Transfer.builder()
                    .id(transferDto.getId())
                    .cuenta_origen(transferDto.getCuenta_origen())
                    .cuenta_destino(transferDto.getCuenta_destino())
                    .fecha(transferDto.getFecha())
                    .monto(transferDto.getMonto())
                    .build());
            transferDto = TransferMapper.transferToDto(transfer);
            return transferDto;
        } else {
            throw new NoSeEncontroE("No se encontró el ID para la transferencia indicada");
        }
    }

    public String eliminarTransfer(Long id) {
        if(transferRepository.existsById(id)){
            transferRepository.deleteById(id);
            return "Transfer eliminada exitosamente";
        } else {
            return "ID no válido";
        }
    }


    // TODO: Agregar validaciones. Antes de intentar hacer transf
    // - Cuentas existentes
    // - Dinero suficiente en cuenta (mínimo o máximo)
    // - Saldo disponible
    // - Que otra cuenta haya recibido el dinero


    //Lógica para realizar transferencias
    //TODO: llamar al método en el momento de la creación desde @Service
    @Transactional
    public TransferDto realizarTransferencia(Long idOrigen, Long idDestino, BigDecimal monto) throws SaldoInsuficiente, NoSeEncontroE {

        // Buscar y ver si existen las cuentas
        Account accountOrigen = accountRepository.findById(idOrigen).orElse(null);
        Account accountDestino = accountRepository.findById(idDestino).orElse(null);

        TransferDto transferDto = null;
        if (accountOrigen.getId() != null && accountDestino.getId() != null) {
            //comprobar si cuenta origen tiene saldo suficiente para el monto a debitar
            if (accountOrigen.getSaldo().compareTo(monto) < 0) {
                throw new SaldoInsuficiente("Saldo insuficiente");
            }

                // Realizar la transferencia
                BigDecimal nuevoSaldoOrigen = accountService.egresarDinero(monto, idOrigen);
                BigDecimal nuevoSaldoDestino = accountService.ingresarDinero(monto, idDestino);

                //3. setear los nuevos saldos a las cuentas
                accountOrigen.setSaldo(nuevoSaldoOrigen);
                accountDestino.setSaldo(nuevoSaldoDestino);

                //4. guardamos cuentas actualizadas
                accountRepository.save(accountOrigen);
                accountRepository.save(accountDestino);

            //TODO: controlar que se hayan realizado ambas operaciones
            // antes de guardar en BBDD

            //5. creamos Transfer y guardamos en BBDD

            // creamos un objeto de tipo Date para obtener fecha actual
            //BUSCAR: java.time libreria
            Date fechaActual = new Date();

                Transfer transfer = Transfer.builder()
                        .cuenta_origen(accountOrigen.getNum_cuenta())
                        .cuenta_destino(accountDestino.getNum_cuenta())
                        .monto(monto)
                        .fecha(fechaActual)
                        .build();
                transfer = transferRepository.save(transfer);
                transferDto = TransferMapper.transferToDto(transfer);
            }

        return transferDto;
    }

}
