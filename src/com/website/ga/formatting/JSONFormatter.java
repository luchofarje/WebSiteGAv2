package com.website.ga.formatting;

import java.io.IOException;

import com.website.ga.QueryResult;

public class JSONFormatter implements IFormatter{

	private QueryResult _queryResult;
	
	public JSONFormatter(QueryResult queryResult){
		_queryResult = queryResult;

	}
	
	public String getFormattedString(){
		try {
			return _queryResult.GetAsJson();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
