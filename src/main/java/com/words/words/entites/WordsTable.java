package com.words.words.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.words.words.constants.WordsConstants;

@Entity
@Table(name =WordsConstants.TableNames.WORDS)
@JsonSerialize 
public class WordsTable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8158612398046645667L;

	@Id
	@Column(name = WordsConstants.ColumnNames.WordsTable.ID)
	private Integer id;
	
	@Column(name = WordsConstants.ColumnNames.WordsTable.WORD)
	private String word;
	
	@Column(name = WordsConstants.ColumnNames.WordsTable.MEANING)
	private String meaning;

	public WordsTable() {
	}

	public WordsTable(String word, String meaning) {
		this.word = word;
		this.meaning = meaning;
	}

	public WordsTable(Integer id, String word, String meaning) {
		this.id = id;
		this.word = word;
		this.meaning = meaning;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
		

}
