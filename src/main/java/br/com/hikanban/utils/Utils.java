package br.com.hikanban.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.core.io.Resource;

import br.com.hikanban.exception.InvalidValueParameterException;
import br.com.hikanban.exception.ParseDateException;

/**
 * This class does all default system logics
 *
 */
public class Utils {
	
	private static final String LIKE = "%";

	/**
	 * Get Long from result query information
	 * @param result the result query information
	 * @return the long of result
	 */
	public static Long getLong(Object result) {
		Long value = null;
		if (result != null) {
			value = Long.valueOf(getString(result));
		}
		return value;
	}
	
	/**
	 * Get Date from result query information
	 * @param result the result query information
	 * @return the date of result
	 * @throws ParseException 
	 */
	public static Date getDate(Object result) {
		Date value = null;
		if (result != null) {
			try {
				value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getString(result));
			}catch (Exception e) {
				throw new ParseDateException();
			}
			
		}
		return value;
	}
	
	/**
	 * Get Integer from result query information
	 * @param result the result query information
	 * @return the Integer of result
	 */
	public static Integer getInteger(Object result) {
		Integer value = null;
		if (result != null) {
			value = Integer.valueOf(getString(result));
		}
		return value;
	}
	
	/**
	 * Get string from result query information
	 * @param result the result query information
	 * @return the string of result
	 */
	public static String getString(Object result) {
		String value = null;
		if (result != null) {
			value = String.valueOf(result);
		}
		return value;
	}
	
	/**
	 * Get boolean from result query information
	 * @param result the result query information
	 * @return the boolean of result
	 */
	public static Boolean getBoolean(Object result) {
		Boolean value = null;
		if (result != null) {
			value = BooleanUtils.toBoolean(getInteger(result));
		}
		return value;
	}
	
	/**
	 * Add Percent string constant to parameter to execute LIKE query 
	 * @param parameter the parameter to concat with percent string
	 * @return the parameter with percent string constant
	 */
	public static String addLike(String parameter) {
		String parameterLike = parameter;
		if(StringUtils.isNotBlank(parameter)) {
			parameterLike = parameter.concat(LIKE);
		}
		return parameterLike;
	}
	
	/**
	 * Change value informations if and only if the new value if not null and different than old one
	 * @param <T>
	 * @param oldValue the old value information
	 * @param newValue the new value information
	 * @return the the new value information if its not null and different than old value
	 */
	public static <T> T changeValue(T oldValue, T newValue) {
		if(oldValue != null && newValue != null) {
			if(oldValue instanceof String && newValue instanceof String) {
				String databaseValue = (String) oldValue;
				String uservalue = (String) newValue;
				if (StringUtils.isNotBlank(uservalue) && !uservalue.equals(databaseValue)) {
					return newValue;
				}
				return oldValue;
			} else if(oldValue instanceof Long && newValue instanceof Long) {
				Long databaseValue = (Long) oldValue;
				Long uservalue = (Long) newValue;
				if (uservalue != null && !uservalue.equals(databaseValue)) {
					return newValue;
				}
				return oldValue;
			} else if(oldValue instanceof Boolean && newValue instanceof Boolean) {
				Boolean databaseValue = (Boolean) oldValue;
				Boolean uservalue = (Boolean) newValue;
				if (uservalue != null && !uservalue.equals(databaseValue)) {
					return newValue;
				}
				return oldValue;
			} else if(oldValue instanceof Integer && newValue instanceof Integer) {
				Integer databaseValue = (Integer) oldValue;
				Integer uservalue = (Integer) newValue;
				if (uservalue != null && !uservalue.equals(databaseValue)) {
					return newValue;
				}
				return oldValue;
			} else if(oldValue instanceof Date && newValue instanceof Date) {
				Date databaseValue = (Date) oldValue;
				Date uservalue = (Date) newValue;
				if (uservalue != null && !uservalue.equals(databaseValue)) {
					return newValue;
				}
				return oldValue;
			} else if(oldValue instanceof BigDecimal && newValue instanceof BigDecimal) {
				BigDecimal databaseValue = (BigDecimal) oldValue;
				BigDecimal uservalue = (BigDecimal) newValue;
				if (uservalue != null && !uservalue.equals(databaseValue)) {
					return newValue;
				}
				return oldValue;
			} else {
				throw new InvalidValueParameterException();
			}
		} else if(oldValue == null && newValue == null) {
			return null;
		} else if(oldValue == null) {
			return newValue;
		} else {
			return oldValue;
		}
	}
	
	/**
	 * Validate if given CPF is valid
	 * 
	 * @param cpf The value to be validated
	 * @return TRUE if valid, FALSE otherwise
	 */
	public static Boolean validateCPF(String cpf) {
		
		cpf = formatDocument(cpf);
		
		String cpfDigits = StringUtils.substring(cpf, 0, 9);
		Integer coefficient = 10;
		Integer firstDV = sumCPFValues(cpfDigits, coefficient);
		
		coefficient = 11;
		Integer secondDV = sumCPFValues(cpfDigits.concat(cpf.substring(9, 10)), coefficient);
		
		cpfDigits = cpfDigits.concat(firstDV.toString());
		cpfDigits = cpfDigits.concat(secondDV.toString());
				
		return StringUtils.equals(cpf, cpfDigits);
	}
	
    /**
	 * Format CPF or CNPJ.
	 *
	 * @param inputValue the input value
	 * @return the string
	 */
	public static String formatCPFOrCNPJ(String inputValue) {

		if (StringUtils.isBlank(inputValue)) {
			return null;
		}

		if (inputValue.length() == 11) {
			return formatCPF(inputValue);
		}

		return formatCNPJ(inputValue);
	}

	/**
GENERAL_ERROR_VALIDATION;	 * Format CPF.
	 *
	 * @param inputValue the input value
	 * @return the string
	 * @throws ParseException the parse exception
	 */
	public static String formatCPF(String inputValue) {

		if (StringUtils.isBlank(inputValue)) {
			return null;
		}

		if (inputValue.length() < 11) {
			return inputValue;
		}

		StringBuilder builder = new StringBuilder()
				.append(StringUtils.substring(inputValue, 0, 3))
				.append('.')
				.append(StringUtils.substring(inputValue, 3, 6))
				.append('.')
				.append(StringUtils.substring(inputValue, 6, 9))
				.append('-')
				.append(StringUtils.substring(inputValue, 9));

		return builder.toString();
	}

	/**
	 * Format CNPJ.
	 *
	 * @param inputValue the input value
	 * @return the string
	 */
	public static String formatCNPJ(String inputValue) {

		if (StringUtils.isBlank(inputValue)) {
			return null;
		}

		if (inputValue.length() < 14) {
			return inputValue;
		}

		StringBuilder builder = new StringBuilder()
				.append(StringUtils.substring(inputValue, 0, 2))
				.append('.')
				.append(StringUtils.substring(inputValue, 2, 5))
				.append('.')
				.append(StringUtils.substring(inputValue, 5, 8))
				.append('/')
				.append(StringUtils.substring(inputValue, 8, 12))
				.append('-')
				.append(StringUtils.substring(inputValue, 12));

		return builder.toString();
	}

	/**
	 * Format CEP.
	 *
	 * @param inputValue the input value
	 * @return the string
	 * @throws ParseException the parse exception
	 */
	public static String formatCEP(String inputValue) {

		if (StringUtils.isBlank(inputValue)) {
			return null;
		}

		if (inputValue.length() < 8) {
			return inputValue;
		}

		return StringUtils.join(StringUtils.substring(inputValue, 0, 5), "-", StringUtils.substring(inputValue, 5));
	}
	
	/**
	 * Validate if given CNPJ is valid
	 * 
	 * @param cnpj The value to be validated
	 * @return TRUE if valid, FALSE otherwise
	 */
	public static Boolean validateCNPJ(String cnpj) {
		
		cnpj = formatDocument(cnpj);
		
		StringBuffer cnpjDigitsBuffer = new StringBuffer(StringUtils.substring(cnpj, 0, 12));
		Integer coefficient = 2;
		Integer firstDV = sumCNPJValues(cnpjDigitsBuffer.reverse().toString(), coefficient);
		
		coefficient = 2;
		Integer secondDV = sumCNPJValues(cnpjDigitsBuffer.insert(0, cnpj.substring(12, 13)).toString(), coefficient);
		
		cnpjDigitsBuffer = new StringBuffer(StringUtils.substring(cnpj, 0, 12));
		cnpjDigitsBuffer = cnpjDigitsBuffer.append(firstDV);
		cnpjDigitsBuffer = cnpjDigitsBuffer.append(secondDV);
		return StringUtils.equals(cnpj, cnpjDigitsBuffer.toString());
	}
	
	/**
	 * Format given document removing special characters
	 * 
	 * @param document The document to be formatted
	 * @return The document formatted
	 */
	public static String formatDocument(String document) {
		document = StringUtils.replace(document, "-", "");
		document = StringUtils.replace(document, ".", "");
		document = StringUtils.replace(document, "/", "");
		document = StringUtils.replace(document, " ", "");
		return document;
	}
	
	public static String formatPhone(String phone) {
		phone = StringUtils.replace(phone, "-", "");
		phone = StringUtils.replace(phone, " ", "");
		phone = StringUtils.replace(phone, "(", "");
		phone = StringUtils.replace(phone, ")", "");
		return phone;
	}
	
	/**
	 * Sum the CPF digits according coefficient
	 * 
	 * @param cpfDigits The cpfDigits base
	 * @param coefficient The coefficient
	 * @return The sum value
	 */
	private static Integer sumCPFValues(String cpfDigits, Integer coefficient) {
		Integer dv;
		Integer sum = 0;
		for(Integer index = 0; index < cpfDigits.length(); index++) {
			sum += (Character.getNumericValue(cpfDigits.charAt(index)) * coefficient);
			coefficient--;
		}
		
		dv = 11 - (sum % 11);
		if(dv < 2) {
			dv = 0;
		}
		return dv;
	}
	
	/**
	 * Sum the CNPJ digits according coefficient
	 * 
	 * @param cnpjDigits The cpfDigits base
	 * @param coefficient The coefficient
	 * @return The sum value
	 */
	private static Integer sumCNPJValues(String cnpjDigits, Integer coefficient) {
		Integer dv;
		Integer sum = 0;
		for(Integer index = 0; index < cnpjDigits.length(); index++) {
			sum += (Character.getNumericValue(cnpjDigits.charAt(index)) * coefficient);
			coefficient++;
			if(coefficient == 10) {
				coefficient = 2;
			}
		}
		
		dv = 11 - (sum % 11);
		if(dv < 2) {
			dv = 0;
		}
		return dv;
	}
	
	/**
	 * Validate phone number 
	 * 
	 * @author diva
	 * @param The phone number
	 * @return TRUE if valid, FALSE otherwise
	 */
	public static boolean isPhone(String phone) {
        return phone.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}") ||
                phone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}");
    }
	
	public static String generateRandomPassword(int length) {
        String NumericString = "0123456789";
  
        StringBuilder sb = new StringBuilder(length); 
  
        for (int i = 0; i < length; i++) {
        	String currentStr = "";
        	if (length / 4 >= i) {
        		currentStr = NumericString;
        	} else if (length / 4 * 2 >= i) {
        		currentStr = NumericString;
        	} else if (length / 4 * 3 >= i) {
        		currentStr = NumericString;
        	} else {
        		currentStr = NumericString;
        	}
  
            int index = (int)(currentStr.length() * Math.random());
  
            sb.append(currentStr.charAt(index)); 
        }
  
        return sb.toString(); 
	}
	
	/**
	 * Draw image on PDF.
	 *
	 * @param document the document
	 * @param page the page
	 * @param base64Image the base 64 image
	 * @param posX the pos X
	 * @param posY the pos Y
	 * @param newHeight the new height
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void drawImageOnPDF(PDDocument document, PDPage page, String base64Image, float posX, float posY, int newHeight) throws IOException {

		if (StringUtils.isBlank(base64Image)) {
			return;
		}
		
		try (ByteArrayInputStream bais = new ByteArrayInputStream(Base64.getDecoder().decode(base64Image))) {
			drawImageOnPDF(document, page, bais, posX, posY, newHeight);
		}
	}
	
	/**
	 * Draw image on PDF.
	 *
	 * @param document the document
	 * @param page the page
	 * @param imageResource the image resource
	 * @param posX the pos X
	 * @param posY the pos Y
	 * @param newHeight the new height
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void drawImageOnPDF(PDDocument document, PDPage page, Resource imageResource, float posX, float posY, int newHeight) throws IOException {

		try (InputStream is = imageResource.getInputStream()) {
			drawImageOnPDF(document, page, is, posX, posY, newHeight);
		}
	}

	/**
	 * Draw image on PDF.
	 *
	 * @param document the document
	 * @param page the page
	 * @param bufferedImage the buffered image
	 * @param posX the pos X
	 * @param posY the pos Y
	 * @param newHeight the new height
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void drawImageOnPDF(PDDocument document, PDPage page, InputStream imageStream, float posX, float posY, int newHeight)
			throws IOException {
		
		BufferedImage bufferedImage = ImageIO.read(imageStream);
		
		if (newHeight > 0) {
			int newWidth = calculateNewWidth(bufferedImage, newHeight);
			bufferedImage = Scalr.resize(bufferedImage, Method.ULTRA_QUALITY, Mode.FIT_TO_HEIGHT, newWidth, newHeight, Scalr.OP_ANTIALIAS);

			// Mode.FIT_TO_HEIGHT
			int cropX = (bufferedImage.getWidth() - newWidth) / 2;
			if (cropX != 0) {
				bufferedImage = Scalr.crop(bufferedImage, cropX, 0, newWidth, newHeight);
			}
		}

		PDImageXObject imageXObject = LosslessFactory.createFromImage(document, bufferedImage);

		try (PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.PREPEND, false, false)) {
			contentStream.drawImage(imageXObject, posX, posY);
		}
	}
	
	/**
	 * Calculate new width.
	 *
	 * @param bufferedImage the buffered image
	 * @param newHeight the new height
	 * @return the int
	 */
	private static int calculateNewWidth(BufferedImage bufferedImage, int newHeight) {

		double proportion = (100.0 * newHeight) / bufferedImage.getHeight();

		return (int)Math.ceil((proportion / 100.0) * bufferedImage.getWidth());
	}
}
