package com.conspring.banco.infrastructure.repositories;

import com.conspring.banco.domain.models.Account;
import com.conspring.banco.domain.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository <Transfer, Long> {


}
