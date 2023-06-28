package com.conspring.banco.application.services;

import com.conspring.banco.api.dtos.AccountDto;
import com.conspring.banco.api.mappers.AccountMapper;
import com.conspring.banco.api.mappers.UserMapper;
import com.conspring.banco.domain.models.Account;
import com.conspring.banco.infrastructure.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void eliminarCuenta(Long id){
        accountRepository.deleteById(id);
    }

    public AccountDto modificarCuenta(AccountDto accountDto) {
        Account account = accountRepository.save(Account.builder()
                        .id(accountDto.getId())
                        .num_cuenta(accountDto.getNum_cuenta())
                        .saldo(accountDto.getSaldo())
                        .build());
        accountDto = AccountMapper.accountToDto(account);
        return accountDto;
    }
}
