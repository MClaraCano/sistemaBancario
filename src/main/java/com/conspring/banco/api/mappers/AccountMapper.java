package com.conspring.banco.api.mappers;

import com.conspring.banco.api.dtos.AccountDto;
import com.conspring.banco.domain.models.Account;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    //Sin Builder
    public Account dtoToAccount(AccountDto accountDto){
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setNum_cuenta(accountDto.getNum_cuenta());
        account.setSaldo(accountDto.getSaldo());
        account.setOwner(accountDto.getOwner());
        return account;
    }

    //Con Builder (arrobado en AccountDto)
    public AccountDto accountToDto (Account account){
        AccountDto cuentaDto = AccountDto.builder()
                .id(account.getId())
                .num_cuenta(account.getNum_cuenta())
                .saldo(account.getSaldo())
                .owner(account.getOwner())
                .build();
        return cuentaDto;
    }

}
