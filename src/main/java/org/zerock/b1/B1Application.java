package org.zerock.b1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //JPA에서 감사를 활성화
public class B1Application {

	public static void main(String[] args) {
		SpringApplication.run(B1Application.class, args);
	}

}
