package com.words.words;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.words.words.dao.WordsDao;

@ComponentScan({

})

@SpringBootApplication
public class WordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordsApplication.class, args);
	}
	
	@Bean
	WordsDao getWordsDao() {
		return new WordsDao();
	}

}
