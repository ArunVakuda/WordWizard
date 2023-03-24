package com.words.words.controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.words.words.entites.WordsTable;
import com.words.words.requests.out.WordsOut;
import com.words.words.services.WordsService;

@RestController
@RequestMapping("/words")
public class WordsController {

	@Autowired
	private WordsService ws;

	@GetMapping("/getWords")
	public Optional<WordsTable> getWords(@RequestParam("id") Integer id) {		
		return this.ws.getWordsById(id);
	}

	@GetMapping("/wordLike")
	public List<WordsTable> wordLike(@RequestParam("startLetter") String startLetter,
			@RequestParam("exactLetters") Boolean exactLetters,
			@RequestParam("unique") Boolean unique) throws Exception {
		try {		
			return this.ws.wordLike(startLetter, exactLetters, unique);
		} catch(EntityNotFoundException e) {
			throw e;
		}
	}
	
	@GetMapping("/wordLikeLessLeter")
	public List<WordsOut> wordLikeLessLetter(@RequestParam("startLetter") String startLetter,
			@RequestParam("exactLetters") Boolean exactLetters,
			@RequestParam("unique") Boolean unique) throws Exception {
		try {		
			return this.ws.wordLikeLessLetters(startLetter, exactLetters, unique);
		} catch(EntityNotFoundException e) {
			throw e;
		}
	}

	@PutMapping("/addWord")
	public void addWord() throws Exception {
		try {
			this.ws.addWordEntity();
		} catch(Exception e) {
			throw e;
		}
	}	

}
