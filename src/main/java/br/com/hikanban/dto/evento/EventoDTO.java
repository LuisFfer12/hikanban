package br.com.hikanban.dto.evento;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EventoDTO {
	
	private Integer id;
	private String nome;
	private String endereco;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date data;
	private String horario;
	private String categoria;

	private Integer anuncianteId;


}
