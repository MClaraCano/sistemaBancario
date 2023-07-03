package com.conspring.banco.api.mappers;

import com.conspring.banco.api.dtos.TransferDto;
import com.conspring.banco.domain.models.Transfer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {

    public TransferDto transferToDto(Transfer transfer){
        TransferDto transferDto = TransferDto.builder()
                .id(transfer.getId())
                .cuenta_origen(transfer.getCuenta_origen())
                .cuenta_destino(transfer.getCuenta_destino())
                .fecha(transfer.getFecha())
                .saldo(transfer.getSaldo())
                .build();
        return transferDto;
    }

    public Transfer dtoToTransfer(TransferDto transferDto){
        Transfer transfer = Transfer.builder()
                .id(transferDto.getId())
                .cuenta_origen(transferDto.getCuenta_origen())
                .cuenta_destino(transferDto.getCuenta_destino())
                .fecha(transferDto.getFecha())
                .saldo(transferDto.getSaldo())
                .build();
        return transfer;
        }

}
