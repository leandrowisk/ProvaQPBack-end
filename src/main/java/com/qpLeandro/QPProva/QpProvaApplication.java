package com.qpLeandro.QPProva;
import com.qpLeandro.QPProva.model.UsuarioModel;
import com.qpLeandro.QPProva.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QpProvaApplication {

	public static void main(String[] args) {
		SpringApplication.run(QpProvaApplication.class, args);
	}

	@Bean
    CommandLineRunner criarAdmin(
			UsuarioRepository repository,
			@Value("${app.admin.password}") String senha) {

		return args -> {

			if (repository.findByEmail("admin@admin.com").isEmpty()) {

				UsuarioModel admin = new UsuarioModel();

				admin.setEmail("admin@admin.com");
				admin.setPassword(senha);

				repository.save(admin);
			}
		};
	}
}
