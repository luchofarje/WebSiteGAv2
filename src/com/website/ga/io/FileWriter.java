package com.website.ga.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;

import com.google.common.io.Files;
import com.website.ga.ConfigManager;

public class FileWriter {

	public enum FileExtension {
		json, csv
	};

	private static String TMP_FILE_SUFFIX = ".tmp";

	private ConfigManager _configManager;

	private FileNameHelper _fileNameHelper;

	static Logger logger = Logger.getLogger(FileWriter.class.getName());

	public FileWriter(ConfigManager configManager, FileNameHelper fileNameHelper) {
		_configManager = configManager;
		_fileNameHelper = fileNameHelper;
	}

	public void Write(String data) {
		File outputFile = new File(getAbsOutputFileName());
		File outputFileTmp = new File(getAbsOutputFileName() + TMP_FILE_SUFFIX);
		File tempFile = new File(getAbsTempFileName());

		createPath(outputFile);
		createPath(tempFile);

		try {
			Files.write(data, tempFile, StandardCharsets.UTF_8);
			Files.copy(tempFile, outputFileTmp);
			Files.move(outputFileTmp, outputFile);
		} catch (IOException e) {
			logger.error("Failed to create,  copy or move file.", e);
		}
	}

	public String GetOutputFileName() {
		return getAbsOutputFileName();
	}

	private void createPath(File file) {
		File path = new File(file.getParent());
		path.mkdirs();
	}

	private String getAbsOutputFileName() {
		String absFileName = String.format("%s/%s",
				_configManager.getOutputFolder(),
				_configManager.getOutputFile());
		return _fileNameHelper.getFileName(absFileName);
	}

	private String getAbsTempFileName() {
		String absFileName = String.format("%s/%s",
				_configManager.getTempFolder(), _configManager.getTempFile());
		return _fileNameHelper.getFileName(absFileName);
	}

}
