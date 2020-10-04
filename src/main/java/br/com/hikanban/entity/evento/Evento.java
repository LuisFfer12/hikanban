package br.com.hikanban.entity.evento;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.hikanban.entity.anunciante.Anunciante;
import br.com.hikanban.enums.CategoriaEventoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "evento")
@SuppressWarnings("serial")
public class Evento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String endereco;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date data;
	
	private String horario;
	
	private String banner;
	
	@Enumerated(EnumType.STRING)
	private CategoriaEventoEnum categoria;

	@ManyToOne
	@JoinColumn(name="anunciante_id")
	private Anunciante anunciante;


}
