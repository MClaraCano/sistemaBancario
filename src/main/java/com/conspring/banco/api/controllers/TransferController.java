package com.conspring.banco.api.controllers;

import com.conspring.banco.api.dtos.TransferDto;
import com.conspring.banco.application.services.TransferService;
import com.conspring.banco.domain.exceptions.NoSeEncontroE;
import com.conspring.banco.domain.models.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @GetMapping("/transfers")
    public ResponseEntity<List<TransferDto>> getTransfers(){
        List<TransferDto> listTransfer = transferService.obtenerTransferencias();
        return ResponseEntity.status(200).body(listTransfer);
    }

    @GetMapping("/transfers/{id}")
    public ResponseEntity<TransferDto> getTransfer(@PathVariable Long id){
        TransferDto transferDto = transferService.getTransferById(id);
        return ResponseEntity.status(200).body(transferDto);
    }

    @PostMapping("/creartransfer")
    public ResponseEntity<TransferDto> crearTransfer(@RequestBody TransferDto transferDto){
        transferDto = transferService.crearTransfer(transferDto);
        return ResponseEntity.status(201).body(transferDto);
    }

    @PutMapping("/modificartransfer")
    public ResponseEntity<TransferDto> modificarTransfer(@RequestBody TransferDto transferDto) throws NoSeEncontroE {
        transferDto = transferService.modificarTransfer(transferDto);
        return ResponseEntity.status(200).body(transferDto);
    }
}
