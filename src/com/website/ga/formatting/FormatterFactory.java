package com.website.ga.formatting;

import org.apache.log4j.Logger;
import com.website.ga.QueryResult;

public class FormatterFactory {
	
	static Logger logger = Logger.getLogger(FormatterFactory.class.getName());

	public static IFormatter getFormatter(OutputFormat format, QueryResult queryResult){
		IFormatter formatter = null;
		switch (format) {
		case csv:
			formatter = new CSVFormatter(queryResult);
			break;
		case json:
			formatter = new JSONFormatter(queryResult);
			break;
		default:
			logger.fatal("Unsupported formatter: " + format.toString());
			break;
		}
		return formatter;
	}

}
