package com.conspring.banco;

import com.conspring.banco.api.dtos.UserDto;
import com.conspring.banco.domain.models.Account;
import com.conspring.banco.domain.models.Transfer;
import com.conspring.banco.domain.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;

@SpringBootApplication
@EnableJpaRepositories // habilitamos al proyecto trabajar con los repo
public class BancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
		System.out.println("Andando ok");

		//práctica @Builder: construye con los parámetros que queramos
		UserDto userDto = UserDto.builder().password("45").username("Cla").build();
		System.out.println(userDto);

		User user = new User();
		User user1 = new User();

		BigDecimal numero = BigDecimal.valueOf(100);
		BigDecimal numero2 = BigDecimal.valueOf(50);

		Account account1 = new Account(4L, 25, numero, user);
		Account account2 = new Account(5L, 89, numero2, user1);

		Transfer transfer = new Transfer();
		

	}

}
