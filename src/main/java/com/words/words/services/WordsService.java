package com.words.words.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.words.words.dao.WordsDao;
import com.words.words.entites.WordsTable;
import com.words.words.repositories.WordsRepository;
import com.words.words.requests.out.WordsOut;

@Service
public class WordsService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EntityManager em; 
	
	@Autowired
	private WordsRepository wr;
	
	@Autowired
	private WordsDao dao;
	
	public Optional<WordsTable> getWordsById(Integer id){
		this.wr.save(new WordsTable(2, "Cube", "3D Shape having 6 sides"));
		return this.wr.findById(id);
	}
	
	public List<WordsTable> wordLike(String startLetter, Boolean excactLetters, Boolean unique) throws Exception {
		String[] strSplit = startLetter.toLowerCase().split("");
		List<String> charList = new ArrayList<>(Arrays.asList(strSplit));
		if(charList.size()<3) {
			throw new EntityNotFoundException("Word Size min 3 letters");
		}
		log.trace("[TEST] "+"startLetter");
		
		return this.dao.wordList(charList, excactLetters, unique);
	}
	
	public List<WordsOut> wordLikeLessLetters(String startLetter, Boolean excactLetters, Boolean unique) throws Exception {
		String[] strSplit = startLetter.toLowerCase().split("");
		List<String> charList = new ArrayList<>(Arrays.asList(strSplit));
		if(charList.size()<3) {
			throw new EntityNotFoundException("Word Size min 3 letters");
		}
		List<WordsOut> outWords = new ArrayList<>();
		List<WordsTable> wordFullList = this.dao.wordList(charList, excactLetters, unique);
		for(int i = 3; i <= startLetter.length(); i++) {
			List<WordsTable> wl = new ArrayList<>();
			for(WordsTable w : wordFullList) {
				if(w.getWord().length() == i) {
					wl.add(w);					
				}
			}
			WordsOut w1 = new WordsOut(i, wl);
			outWords.add(w1);
		}		
		return outWords;
	}
	
	public void addWordEntity() throws Exception {
		try {
		String query = "SELECT MAX(id) FROM words";
		Integer id = (Integer) em.createNativeQuery(query).getSingleResult();			
		WordsTable w = new WordsTable(++id, "Cubical", "A workspace employees");
		this.wr.save(w);
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
