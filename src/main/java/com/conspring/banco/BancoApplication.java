package com.conspring.banco;

import com.conspring.banco.api.dtos.UserDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories // habilitamos al proyecto trabajar con los repo
public class BancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
		System.out.println("Andando ok");

		//práctica @Builder: construye con los parámetros que queramos
		UserDto userDto = UserDto.builder().password("45").username("Cla").build();
		System.out.println(userDto);

	}

}
