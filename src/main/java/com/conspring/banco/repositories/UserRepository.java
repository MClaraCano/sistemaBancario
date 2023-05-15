package com.conspring.banco.repositories;

import com.conspring.banco.models.User;
import com.conspring.banco.models.dtos.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
