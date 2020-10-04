package br.com.hikanban.utils;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.hikanban.utils.dto.PageRequestDTO;

public class PageUtils {

	private static final Integer DEFAULT_MAX_SIZE = 100;


	public static Pageable getPagination(PageRequestDTO pageRequestDTO, Integer listMaxSize) {
		Integer page = 0;
		Integer size = DEFAULT_MAX_SIZE;
		if (listMaxSize == null) {
			listMaxSize = DEFAULT_MAX_SIZE;
		}
		if (pageRequestDTO != null) {
			page = pageRequestDTO.getPage();
			size = pageRequestDTO.getSize();
			if (size == null || size > listMaxSize) {
				size = listMaxSize;
			}
			if (page == null || --page < 0) {
				page = 0;
			}
		}
		return PageRequest.of(page, size);
	}
}
