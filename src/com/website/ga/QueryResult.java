package com.website.ga;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.GaData.ColumnHeaders;

public class QueryResult {
	
	private List<GaData> _gaDatas;
	
	public QueryResult(){
		_gaDatas = new ArrayList<GaData>();
	}
	
	public void addGaData(GaData gaData) {
		_gaDatas.add(gaData);
	}

	public boolean isSuccessful() {
		return _gaDatas.size() > 0;
	}

	public List<String> getHeaders(){
		List<String> headers = new ArrayList<String>();
		for ( ColumnHeaders header : _gaDatas.get(0).getColumnHeaders()){
			headers.add(header.getName());
		}
		return headers;
	}
	
	public List<List<String>> getRows(){
		List<List<String>> allRows = new ArrayList<List<String>>();
		for ( GaData gaData : this._gaDatas){
			allRows.addAll(gaData.getRows());
		}
		return allRows;
	}
	
	public String GetAsJson() throws IOException{
		GaData gaData = _gaDatas.get(_gaDatas.size()-1); 
		gaData.setRows(getRows());
		return gaData.toPrettyString();
	}
}
