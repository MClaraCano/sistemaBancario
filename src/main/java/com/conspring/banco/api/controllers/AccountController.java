package com.conspring.banco.api.controllers;

import com.conspring.banco.api.dtos.AccountDto;
import com.conspring.banco.application.services.AccountService;
import com.conspring.banco.domain.exceptions.NoSeEncontroE;
import com.conspring.banco.domain.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getCuentas(){
        List<AccountDto> listaDto = accountService.getCuentas();
        return ResponseEntity.status(200).body(listaDto);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDto> getCuenta(@PathVariable Long id){
        AccountDto accountDto = accountService.getCuenta(id);
        return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    }

    @PostMapping("/crearcuenta")
    public ResponseEntity<AccountDto> crearCuenta(@RequestBody AccountDto accountDto){
        accountDto = accountService.crearCuenta(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    }

    @PutMapping("/modificarcuenta/{id}")
    public ResponseEntity<AccountDto> modificarCuenta (@PathVariable Long id, @RequestBody AccountDto accountDto) throws NoSeEncontroE {
        accountDto = accountService.modificarCuenta(id, accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    }

    @DeleteMapping("/eliminarcuenta/{id}")
    public ResponseEntity<String> eliminarCuenta(@PathVariable Long id){
        //Al poner eso en el body, se usa el String definido en el service
        return ResponseEntity.status(HttpStatus.OK).body(accountService.eliminarCuenta(id));
    }




}
