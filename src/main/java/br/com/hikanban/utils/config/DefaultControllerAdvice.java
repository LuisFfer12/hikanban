package br.com.hikanban.utils.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.hikanban.utils.dto.ErrorResponseDTO;
import br.com.hikanban.utils.exception.DefaultException;
import br.com.hikanban.utils.exception.ErrorResponse;
import br.com.hikanban.utils.exception.GeneralExceptionErrorList;


@SuppressWarnings("rawtypes")
public class DefaultControllerAdvice {
	
        @ResponseBody
        @ExceptionHandler(DefaultException.class)
        public ResponseEntity handle(DefaultException exception) {
            ErrorResponse errorResponse = exception.getErrorResponse();
            List<ErrorResponseDTO> errorResponseList = new ArrayList<ErrorResponseDTO>();
            if(exception instanceof GeneralExceptionErrorList){
            	for(ErrorResponseDTO error : ((GeneralExceptionErrorList) exception).getErrors()) {
            		errorResponseList.add(error);
            	}
            } else {
            	errorResponseList.add(new ErrorResponseDTO(errorResponse.getCode(),errorResponse.getMessage()));
            }
            return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponseList);
        }
}
