package co.com.runt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RuntApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuntApplication.class, args);
	}
}
