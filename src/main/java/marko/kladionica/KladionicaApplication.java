package marko.kladionica;

import marko.kladionica.data.DataSet;
import marko.kladionica.service.selenium.WebDriverService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KladionicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KladionicaApplication.class,args);

	}

}
