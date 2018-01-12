package com.website.ga.formatting;

import java.util.List;

import com.website.ga.QueryResult;

public class CSVFormatter implements IFormatter{
	
	private QueryResult _queryResult;
	
	private StringBuilder _sb;
	
	private static String LINE_SEPARATOR = System.getProperty("line.separator"); 
	
	private static String TOKEN_DELIMITER = ";";
	
	public CSVFormatter(QueryResult queryResult){
		_queryResult = queryResult;
		_sb = new StringBuilder();
	}
	
	public String getFormattedString(){
		appendNewRow(_queryResult.getHeaders());
		
		for ( List<String> row : _queryResult.getRows() ){
			appendNewRow(row);
		}
		return _sb.toString();
	}
	
	private void appendNewRow(List<String> input){
		String delimiter = "";
		
		for (String item : input){
			_sb.append(delimiter + item);
			delimiter = TOKEN_DELIMITER;
		}

		_sb.append(LINE_SEPARATOR);
	}

}
