package com.conspring.banco.application.services;

import com.conspring.banco.api.dtos.AccountDto;
import com.conspring.banco.api.mappers.AccountMapper;
import com.conspring.banco.domain.exceptions.NoSeEncontroE;
import com.conspring.banco.domain.models.Account;
import com.conspring.banco.infrastructure.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountDto> getCuentas(){
        List<Account> listaCuentas = accountRepository.findAll();
        List<AccountDto> listaDto = listaCuentas.stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
        return listaDto;
    }

    public AccountDto getCuenta(Long id){
        Account account = accountRepository.findById(id).orElse(null);
        AccountDto accountDto = AccountMapper.accountToDto(account);
        return accountDto;
    }

    public AccountDto crearCuenta(AccountDto accountDto){
        Account account = accountRepository.save(AccountMapper.dtoToAccount(accountDto));
        accountDto = AccountMapper.accountToDto(account);
        return accountDto;
    }

    // ELIMINAR
    public String eliminarCuenta(Long id){
        if (accountRepository.existsById(id)){
            accountRepository.deleteById(id);
            return "Cuenta eliminada exitosamente";
        } return "No se encontró la cuenta para eliminar";
    }


    // MODIFICAR
    public AccountDto modificarCuenta(Long id, AccountDto accountDto) throws NoSeEncontroE {

        Optional<Account> accountCreadaPorId = accountRepository.findById(id);

        if (accountCreadaPorId.isPresent()){

            //para trabajar sólo con la entidad (con id) del Optional
            Account entidad = accountCreadaPorId.get();

            //pasamos a account el dto que pasamos como parámetro, para poder trabajarlo
            Account accountAModificar = AccountMapper.dtoToAccount(accountDto);

            //seteamos el id de la account con la entidad buscada por id
            accountAModificar.setId(entidad.getId());

            Account accountNueva = accountRepository.save(accountAModificar);
            AccountDto accountDtoADevolver = AccountMapper.accountToDto(accountNueva);
            return accountDtoADevolver;

        } else {
            throw new NoSeEncontroE("No se encontró la cuenta del ID: " + id);
        }
    }


}

/*
        // Error a corregir: Si no existe el ID, crea uno nuevo con los parámetros enviados

        public AccountDto modificarCuenta(AccountDto accountDto) {
        Account account = accountRepository.save(Account.builder()
                        .id(accountDto.getId())
                        .num_cuenta(accountDto.getNum_cuenta())
                        .saldo(accountDto.getSaldo())
                        .build());
        accountDto = AccountMapper.accountToDto(account);
        return accountDto;
    }

 */

