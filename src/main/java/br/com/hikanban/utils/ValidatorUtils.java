package br.com.hikanban.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.hikanban.enums.ErrorResponseEnum;
import br.com.hikanban.utils.dto.ErrorResponseDTO;

/**
 * This class make all system default information validations
 *
 */
public class ValidatorUtils {

	private static final String EMAIL_REGEX = "^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?$";
	
	private static final String PASSWORD_REGEX = "(?=^.{8,}$)(?=.*\\d)(?=.*\\W+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

	/**
	 * Validate if given email is in a valid format
	 * @param email the given email to be validated
	 * @return Boolean.TRUE if the email is valid, otherwise return Boolean.FALSE
	 */
	public static Boolean isEmailValid(String email, List<ErrorResponseDTO> errorResponseList, ErrorResponseEnum errorResponseEnum) {
		Boolean isEmailValid = Boolean.TRUE;
		
		if(StringUtils.isNotBlank(email) && !email.matches(EMAIL_REGEX)) {
			isEmailValid = Boolean.FALSE;
		}
		
		if(!isEmailValid) {
			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(errorResponseEnum.getCode(), errorResponseEnum.getMessage());
			errorResponseList.add(errorResponseDTO);
		}
		
		return isEmailValid;
	}
	
	/**
	 * Validate if given password is in a valid format
	 * @param password The given password to be validated
	 * @return Boolean.TRUE if the password is valid, otherwise return Boolean.FALSE
	 */
	public static Boolean isPasswordValid(String password, List<ErrorResponseDTO> errorResponseList, ErrorResponseEnum errorResponseEnum) {
		Boolean isEmailValid = Boolean.TRUE;
		
		if(StringUtils.isNotBlank(password) && !password.matches(PASSWORD_REGEX)) {
			isEmailValid = Boolean.FALSE;
		}
		
		if(!isEmailValid) {
			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(errorResponseEnum.getCode(), errorResponseEnum.getMessage());
			errorResponseList.add(errorResponseDTO);
		}
		
		return isEmailValid;
	}
	
	/**
	 * Validate if informed values is present
	 * @param value the value to be validated
	 * @return Boolean.TRUE if the is present, otherwise return Boolean.FALSE
	 */
	public static Boolean isValuePresent(Object value, List<ErrorResponseDTO> errorResponseList, ErrorResponseEnum errorResponseEnum) {
		Boolean isValueValid = Boolean.TRUE;
		if(value instanceof String) {
			if(!StringUtils.isNotBlank((String) value)) {
				isValueValid = Boolean.FALSE;
			}
		} else if(value == null) {
			isValueValid = Boolean.FALSE;
		}
		
		if(!isValueValid) {
			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(errorResponseEnum.getCode(), errorResponseEnum.getMessage());
			errorResponseList.add(errorResponseDTO);
		}
		
		return isValueValid;
	}
	
	/**
	 * Validate if informed phone number is valid
	 * @param value the phoneNumber to be validated
	 * @return Boolean.TRUE if the secondValue is valid, otherwise return Boolean.FALSE
	 */
	public static Boolean isPhoneNumberValid(String phoneNumber, List<ErrorResponseDTO> errorResponseList, ErrorResponseEnum errorResponseEnum) {
		Boolean isValueValid = Boolean.TRUE;
		
		phoneNumber = StringUtils.replace(phoneNumber, "-", "");
		if(StringUtils.length(phoneNumber) < 8) {
			isValueValid = Boolean.FALSE;
		} else {
			try {
				Long.valueOf(phoneNumber);
			} catch (NumberFormatException numberException) {
				isValueValid = Boolean.FALSE;
			}
		}
		
		if(!isValueValid) {
			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(errorResponseEnum.getCode(), errorResponseEnum.getMessage());
			errorResponseList.add(errorResponseDTO);
		}
		
		return isValueValid;
	}
	
	/**
	 * Validate if given password is equals to confirmation password
	 * @param password The given password to be validated
	 * @param passwordConfirmation The given matching password
	 * @return Boolean.TRUE if the password is valid, otherwise return Boolean.FALSE
	 */
	public static Boolean isPasswordValid(String password, String passwordConfirmation, List<ErrorResponseDTO> errorResponseList, ErrorResponseEnum errorResponseEnum) {
		Boolean isEmailValid = Boolean.TRUE;
		
		if(StringUtils.isNotBlank(password) && StringUtils.isNotBlank(passwordConfirmation) && !StringUtils.equals(password, passwordConfirmation)) {
			isEmailValid = Boolean.FALSE;
		}
		
		if(!isEmailValid) {
			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(errorResponseEnum.getCode(), errorResponseEnum.getMessage());
			errorResponseList.add(errorResponseDTO);
		}
		
		return isEmailValid;
	}
}
