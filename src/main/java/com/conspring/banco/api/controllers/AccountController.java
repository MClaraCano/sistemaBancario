package com.conspring.banco.api.controllers;

import com.conspring.banco.api.dtos.AccountDto;
import com.conspring.banco.application.services.AccountService;
import com.conspring.banco.domain.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.status(200).body(accountDto);
    }

    @PostMapping("/crearcuenta")
    public ResponseEntity<AccountDto> crearCuenta(@RequestBody Account account){
        AccountDto accountDto = accountService.crearCuenta(account);
        return ResponseEntity.status(201).body(accountDto);
    }

    @PutMapping("/modificarcuenta")
    public ResponseEntity<AccountDto> modificarCuenta (AccountDto accountDto){
        accountDto = accountService.modificarCuenta(accountDto);
        return ResponseEntity.status(200).body(accountDto);
    }

    @DeleteMapping("/eliminarcuenta/{id}")
    public ResponseEntity<String> eliminarCuenta(@PathVariable Long id){
        accountService.eliminarCuenta(id);
        return ResponseEntity.status(200).body("Cuenta eliminada de manera exitosa");
    }




}
