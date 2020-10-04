package br.com.hikanban.exception;

import br.com.hikanban.enums.ErrorResponseEnum;
import br.com.hikanban.utils.exception.DefaultException;
import br.com.hikanban.utils.exception.ErrorResponse;

@SuppressWarnings("serial")
public class WrongEmailOrPasswordException extends DefaultException{

	@Override
	public ErrorResponse getErrorResponse() {
		return ErrorResponseEnum.WRONG_EMAIL_OR_PASSWORD;
	}
}
