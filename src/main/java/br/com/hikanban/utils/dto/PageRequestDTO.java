package br.com.hikanban.utils.dto;

import lombok.Data;

@Data
public class PageRequestDTO {
    private Integer page;
    private Integer size;
    private String sort;
    private String paginationToken;

    public PageRequestDTO(Integer page, Integer size, String sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public PageRequestDTO(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}
