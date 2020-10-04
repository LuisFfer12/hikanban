package br.com.hikanban.utils.exception;

import java.util.List;

import br.com.hikanban.enums.ErrorResponseEnum;
import br.com.hikanban.utils.dto.ErrorResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class GeneralExceptionErrorList extends DefaultException {

	private List<ErrorResponseDTO> errors;
	
	@Override
	public ErrorResponse getErrorResponse() {
		return ErrorResponseEnum.GENERAL_ERROR_VALIDATION;
	}

}
