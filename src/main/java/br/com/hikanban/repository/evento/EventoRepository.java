package br.com.hikanban.repository.evento;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.hikanban.entity.evento.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
	
	@Query(value="select count(*) from evento where endereco = :endereco and data = :data",nativeQuery = true)
	Integer buscaEnderecoData(@Param("endereco") String endereco, @Param("data") Date data);
	
	Optional<Evento> findById(Integer eventoId);
	
	@Query(value="select count(*) from evento where nome = :nome",nativeQuery = true)
	Integer buscaNome(@Param("nome") String nome);
	
	void deleteById(Integer eventoId);
}
