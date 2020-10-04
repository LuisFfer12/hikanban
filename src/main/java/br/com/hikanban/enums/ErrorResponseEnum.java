package br.com.hikanban.enums;

import org.springframework.http.HttpStatus;

import br.com.hikanban.utils.exception.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ErrorResponseEnum implements ErrorResponse {

	EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "ERR004", "Email already Exists"),
	GENERIC_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ERR001", "Generic error"),
	GENERAL_ERROR_VALIDATION(HttpStatus.BAD_REQUEST, "ERR019", "An error occured while validating the information"),
	INVALID_VALUE_PARAMETER(HttpStatus.BAD_REQUEST, "ERR002", "Invalid value parameter"),
	PARSE_DATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ERR003", "Cannot parse date"),
	ANUNCIANTE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "ERR006", "Anunciante Not Found"),
	ANUNCIANTE_ALREADY_EXIST(HttpStatus.INTERNAL_SERVER_ERROR, "ERR007", "Anunciante Already Exist"),
	EVENTO_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "ERR008", "Event already Exists"),
	EVENTO_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "ERR008", "Evento Not Found"),
	FILE_NOT_FOUND(HttpStatus.BAD_REQUEST, "ERR009", "File not found"),
	WRONG_EMAIL_OR_PASSWORD(HttpStatus.BAD_REQUEST, "ERR009", "Wrong email or password"),
	CATEGORIA_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "ERR0018", "Category doesn't exists");
	
	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

}
