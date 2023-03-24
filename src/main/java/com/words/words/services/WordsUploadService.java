package com.words.words.services;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.words.words.entites.WordsTable;
import com.words.words.repositories.WordsRepository;
import com.words.words.utils.CSVHelper;

@Service
public class WordsUploadService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WordsRepository ws;
	
	 public void save(MultipartFile file) {
		    try {
		      List<WordsTable> tutorials = CSVHelper.csvToWordsTables(file.getInputStream());
		      ws.saveAll(tutorials);
		    } catch (IOException e) {
		    	log.error(e.getMessage(), e);
		      throw new RuntimeException("fail to store csv data: " + e.getMessage());
		    }
		  }
}
