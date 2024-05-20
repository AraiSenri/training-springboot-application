package com.example.demo.student;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(
			StudentRepository repository) {
		return args -> {
			
			
			Student arai = new Student(
					"arai",
					"AARRAAII@persol.co.jp",
					"YYYY/MM?DD",
					"都道府県市区町村字町目",
					"経歴",
					"プロフィール"
					);
			
			
			
			repository.saveAll(List.of(arai));
		};
	}
			
}
