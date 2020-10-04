package br.com.hikanban.utils.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import br.com.hikanban.utils.dto.PageResponseDTO;

@SuppressWarnings("rawtypes")
public class PageToPageResponseDTOMapper {
    
	public static PageResponseDTO map(Page page) {
        ModelMapper modelMapper = new ModelMapper();
        PageResponseDTO pageResponseDTO = modelMapper.map(page, PageResponseDTO.class);
        pageResponseDTO.setHasNext(page.hasNext());
        pageResponseDTO.setHasPrevious(page.hasPrevious());
        return pageResponseDTO;
    }
}
