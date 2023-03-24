package com.words.words.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.words.words.entites.WordsTable;
import com.words.words.utils.WordsUtils;

public class WordsDao {
	
	@Autowired
	private EntityManager em;	
		
	public List<WordsTable> wordList(List<String> charList, Boolean excactLetters, Boolean unique){
		List<WordsTable> wtList = new ArrayList<>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM words w WHERE ");
		int i = 0;
		Boolean t = Boolean.TRUE;
		for(String a: charList) {
			query.append("w.word LIKE '%"+a+"%' AND ");
			if(i == charList.size()-2) {
				query.append("w.word LIKE '%"+charList.get(charList.size()-1)+"%'");
				break;		
			}
			i++;
		}				
		if(Boolean.TRUE.equals(excactLetters)) {
			if(Boolean.TRUE.equals(WordsUtils.hasDupllicates(charList))) {
				query.append(WordsUtils.removedList(charList)) ;
			}			
			if(Boolean.TRUE.equals(t) && charList.size() > 3) {
				query.append(" AND LENGTH(WORD) IN (");
				for(int i1 = (charList.size()); i1 > 3; i1--) {
					query.append(i1 + ",")  ;
				}
				query.append("3)");
			} else {
				query.append(" AND LENGTH(WORD) = "+ charList.size() + "");
			}
		}
		
		if(Boolean.TRUE.equals(unique)) {
			query.append(" GROUP BY word");
		}
		query.append(";");
		String nativeQuery = query.toString();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rs = em.createNativeQuery(nativeQuery).getResultList();
		for(Object[] r : rs) {
			WordsTable w = new WordsTable();
			w.setId((Integer) r[0]);
			w.setWord((String) r[2]);
			w.setMeaning((String) r[1]);
			wtList.add(w);
		}		
		
		System.out.print(query+"/n");
		return wtList;
	}
}
