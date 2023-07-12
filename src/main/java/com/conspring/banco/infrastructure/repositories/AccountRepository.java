package com.conspring.banco.infrastructure.repositories;

import com.conspring.banco.domain.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    //Obtener cuenta por number
    @Query(nativeQuery = true, value = "SELECT * FROM account WHERE num_cuenta = :number")
    Account getAccountByNumber(@Param("number") int number);
}
