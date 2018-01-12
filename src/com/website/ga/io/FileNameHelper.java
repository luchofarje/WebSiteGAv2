package com.website.ga.io;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameHelper {

	private Date _date;

	public FileNameHelper(Date date) {
		_date = date;
	}

	public String getFileName(String fileString) {
		return getEvaluatedFile(fileString);
	}

	private String getEvaluatedFile(String fileString) {

		List<String> vars = getValuesToReplace(fileString);
		for (String var : vars) {
			String toReplace = "{" + var + "}";
			String replacement = getDatePart(_date, var);
			fileString = fileString.replace(toReplace, replacement);
		}
		return fileString;
	}

	private String getDatePart(Date date, String formatString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
		return dateFormat.format(date);
	}

	private List<String> getValuesToReplace(String str) {
		List<String> vars = new ArrayList<String>();
		Pattern p = Pattern.compile("\\{([^}]*)\\}");
		Matcher m = p.matcher(str);
		while (m.find()) {
			vars.add(m.group(1));
		}
		return vars;
	}
}
