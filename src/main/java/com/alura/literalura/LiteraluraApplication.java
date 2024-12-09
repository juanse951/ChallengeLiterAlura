package com.alura.literalura;

import com.alura.literalura.principal.Principal;
import com.alura.literalura.service.AutorService;
import com.alura.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroService libroService;
	@Autowired
	private AutorService autorService;

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(autorService,libroService);
		principal.muestraElMenu();
	}
}
