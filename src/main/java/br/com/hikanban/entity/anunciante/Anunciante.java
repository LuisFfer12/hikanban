package br.com.hikanban.entity.anunciante;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.hikanban.entity.evento.Evento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "anunciante")
@SuppressWarnings("serial")
public class Anunciante {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String telefone;
	private String email;
	private String senha;
	private String user;
	private String recoveryToken;
	
	@OneToMany
	private List<Evento> evento;
	
}
