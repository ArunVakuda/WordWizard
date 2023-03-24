package com.words.words.requests.out;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.words.words.entites.WordsTable;

@JsonSerialize 
public class WordsOut {
	
	private Integer wordSize;
	private List<WordsTable> words;
	
	public WordsOut() {
	}

	public WordsOut(Integer wordSize, List<WordsTable> words) {
		this.setWordSize(wordSize);
		this.setWords(words);
	}

	public Integer getWordSize() {
		return wordSize;
	}

	public void setWordSize(Integer wordSize) {
		this.wordSize = wordSize;
	}

	public List<WordsTable> getWords() {
		return words;
	}

	public void setWords(List<WordsTable> words) {
		this.words = words;
	}
	
	

}
