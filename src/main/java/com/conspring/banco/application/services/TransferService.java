package com.conspring.banco.application.services;

import com.conspring.banco.api.dtos.TransferDto;
import com.conspring.banco.api.mappers.TransferMapper;
import com.conspring.banco.domain.exceptions.NoSeEncontroE;
import com.conspring.banco.domain.models.Transfer;
import com.conspring.banco.infrastructure.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    public List<TransferDto> obtenerTransferencias() {
        List<Transfer> listaTransfers = transferRepository.findAll();
        List<TransferDto> listaDto = listaTransfers.stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
        return listaDto;
    }

    public Transfer obtenerId(Long id){
        return transferRepository.findById(id).orElse(null);
    }

    public TransferDto getTransferById(Long id){
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
                    .saldo(transferDto.getSaldo())
                    .build());
            transferDto = TransferMapper.transferToDto(transfer);
            return transferDto;
        } else {
            throw new NoSeEncontroE("No se encontró el ID para la transferencia indicada");
        }
    }



    // TODO: Agregar validaciones. Antes de intentar hacer transf
    // - Cuentas existentes
    // - Dinero suficiente en cuenta (mínimo o máximo)
    // - Saldo disponible
    // - Que otra cuenta haya recibido el dinero


}
