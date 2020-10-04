package br.com.hikanban.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.hikanban.dto.anunciante.AnuncianteDTO;
import br.com.hikanban.dto.anunciante.AnuncianteResponseDTO;

@Service
public interface AnuncianteService {

	//AnuncianteResponseDTO createAnunciante(AnuncianteDTO requestDTO);

	AnuncianteResponseDTO editAnunciante(Integer anuncianteId, AnuncianteDTO requestDTO);

	AnuncianteResponseDTO getAnunciante(Integer anuncianteId);

	List<AnuncianteResponseDTO> getAllAnunciantes();

	void deleteAnunciante(Integer anuncianteId);


}
