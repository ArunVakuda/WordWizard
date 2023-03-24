package com.words.words.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.words.words.entites.WordsTable;

import java.util.List;
import java.util.Optional;

public interface WordsRepository extends CrudRepository<WordsTable, Integer> {
	
	public Optional<WordsTable> findById(Integer id);
	
	@Query("SELECT r FROM WordsTable r WHERE r.word LIKE ':startLetter%'")
	public List<WordsTable> findWordByLetter(String startLetter);

}
