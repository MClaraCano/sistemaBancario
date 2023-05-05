package com.conspring.banco;

import com.conspring.banco.models.dtos.UserDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
		System.out.println("Andando ok");

		//práctica @Builder: construye con los parámetros que queramos
		UserDto userDto = UserDto.builder().password("45").username("Cla").build();
		System.out.println(userDto);

	}

}
