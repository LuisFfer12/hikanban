package br.com.hikanban.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponseDTO {
    private Long totalElements;
    private Integer totalPages;
    private Integer size;
    private Boolean first;
    private Boolean last;
    private Boolean hasNext;
    private Boolean hasPrevious;
    private String paginationToken;
}
