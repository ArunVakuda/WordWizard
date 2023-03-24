package com.words.words.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordsUtils {
	
	private static List<String> alphabets = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g",
			"h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));
	
	public static String removedList(List<String> wordLetters){
		List<String> remaingLetters = alphabets;
		String query = "";
		for(String s: wordLetters) {
			if(remaingLetters.contains(s)) {
				remaingLetters.remove(s);
			}
		}
		for(String s : remaingLetters) {
			query += "AND w.word NOT LIKE ";
			query += "'%"+ s +"%' ";
		}
		return query;
		
	}
	
	public static Boolean hasDupllicates(List<String> charList) {
		Boolean result = Boolean.FALSE;
		for(int i = 0; i < charList.size(); i++) {
			for(int j = i; j < charList.size()-i; j++) {			
				if(charList.get(i).equals(charList.get(j))) {
					result = Boolean.TRUE;
					break;
				}
			}
		}
		return result;
	}
}
