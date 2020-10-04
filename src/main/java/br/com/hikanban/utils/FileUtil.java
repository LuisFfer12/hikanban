package br.com.hikanban.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;


public class FileUtil {

	public static Boolean validateFileSize(File file) throws IOException {
		Boolean isValid = false;
		try {
			Long rowCount = countFileLines(file);
			if(rowCount > 1) {
				isValid = true;
			}
		} catch (IOException exception) {
			throw exception;
		}
		return isValid;
	}
	
	public static Long countFileLines(File file) throws IOException{
		Long rowCount = Files.lines(Paths.get(file.getPath()), StandardCharsets.ISO_8859_1).count();
		return rowCount;
	}
	
	public static void removeAllFilesFromFolder(String folder) {
		try {
			File file = new File(folder);
			if(file.exists()) {
				FileUtils.cleanDirectory(new File(folder));
			} else {
				file.mkdir();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private static final String ALPHA_NUMERIC_STRING = "qwertyuiopasdfghjklzxcvbnm0123456789";
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public static File convert(MultipartFile file) throws IOException {
		String[] fileName = file.getOriginalFilename().split("\\.");
		File convFile = new File(fileName[0] + randomAlphaNumeric(10) + "." + fileName[fileName.length-1]);
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}
