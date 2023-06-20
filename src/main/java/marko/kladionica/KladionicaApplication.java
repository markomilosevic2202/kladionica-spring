package marko.kladionica;

import marko.kladionica.service.MembersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KladionicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KladionicaApplication.class,args);
	}

}
