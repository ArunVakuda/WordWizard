package com.words.words.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.words.words.entites.WordsTable;


public class CSVHelper {
	public static String TYPE = "text/csv";
	static String[] HEADERs = { "id", "word", "meaning"};
	
	private CSVHelper() {
		super();
		// Auto-generated constructor stub
	}

	public static boolean hasCSVFormat(MultipartFile file) {
		
		return TYPE.equals(file.getContentType());
	}

	public static List<WordsTable> csvToWordsTables(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<WordsTable> wordsTables = new ArrayList<>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				WordsTable wordsTable = new WordsTable(
						Integer.parseInt(csvRecord.get(0)),
						csvRecord.get(1),
						csvRecord.get(2)
						);

				wordsTables.add(wordsTable);
			}

			return wordsTables;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	public static ByteArrayInputStream wordsTablesToCSV(List<WordsTable> wordsTables) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (WordsTable WordsTable : wordsTables) {
				List<String> data = Arrays.asList(
						String.valueOf(WordsTable.getId()),
						WordsTable.getWord(),
						WordsTable.getMeaning()

						);

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}
}
