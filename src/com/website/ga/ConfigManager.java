package com.website.ga;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.website.ga.formatting.OutputFormat;
import com.webresources.ga.*;

public class ConfigManager {

	public static final String PROP_FORMAT= "outputFormat";
	public static final String PROP_OUTPUT_FILE= "outputFile";
	public static final String PROP_OUTPUT_FOLDER= "outputFolder";
	public static final String PROP_TEMP_FOLDER= "tempFolder";
	public static final String PROP_TEMP_FILE= "tempFile";
	
	public static final String CONFIG_FILE = "config.properties";
	public static final String PROP_DIMENSIONS = "dimensions";
	public static final String PROP_METRICS = "metrics";
	public static final String PROP_STARTDATE= "startDate";
	public static final String PROP_ENDDATE= "endDate";
	public static final String ACCOUNT_NAME = "accountName";
	public static final String PROP_FILTERS = "filters";
	public static final String PROP_SORTS = "sorts";
	
	static Logger log = Logger.getLogger(ConfigManager.class.getName());
	
	private Properties props; 
	private File configFile;
	
	public ConfigManager(String configFileName){
		configFile = new File(configFileName);
	}
	
	public void loadConfig() throws ConfigurationException {
		try {
			props = new Properties();

			FileInputStream inputStream = new FileInputStream(configFile);

			if (inputStream != null) {
				props.load(inputStream);
			} 
		} catch (Exception e) {
			log.error("An error occured while importing the configuration!", e);
			throw new ConfigurationException(e.getMessage(), e);
		}
	}

	public String getDimensions(){
		return props.getProperty(PROP_DIMENSIONS, "");
	}
	
	public String getEndDate(){
		return props.getProperty(PROP_ENDDATE, "");
	}
	
	public String getFilters(){
		return props.getProperty(PROP_FILTERS, "");
	}
	
	public String getFormat(){
		return props.getProperty(PROP_FORMAT, "");
	}
	
	public String getMetrics(){
		return props.getProperty(PROP_METRICS, "");
	}
	
	public String getSorts(){
		return props.getProperty(PROP_SORTS, "");
	}
	
	public String getStartDate(){
		return props.getProperty(PROP_STARTDATE, "");
	}
	
	public OutputFormat getOutputFormat(){
		String format = props.getProperty(PROP_FORMAT);
		return OutputFormat.valueOf(format);
	}
	
	public String getOutputFolder(){
		return props.getProperty(PROP_OUTPUT_FOLDER, "");
	}
	
	public String getOutputFile(){
		return props.getProperty(PROP_OUTPUT_FILE, "");
	}
	
	public String getTempFolder(){
		return props.getProperty(PROP_TEMP_FOLDER);
	}
	
	public String getTempFile(){
		return props.getProperty(PROP_TEMP_FILE);
	}
	
}
