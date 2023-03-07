package ch.jannis.gluecksrad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ch.jannis.gluecksrad.controller", "ch.jannis.gluecksrad.dao", "ch.jannis.gluecksrad.service", "ch.jannis.gluecksrad.model"})
public class GluecksradApplication {

	public static void main(String[] args) {
		SpringApplication.run(GluecksradApplication.class, args);
	}

}
