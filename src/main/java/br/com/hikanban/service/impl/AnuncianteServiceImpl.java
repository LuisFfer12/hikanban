package br.com.hikanban.service.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

import br.com.hikanban.dto.anunciante.AnuncianteDTO;
import br.com.hikanban.dto.anunciante.AnuncianteResponseDTO;
import br.com.hikanban.entity.anunciante.Anunciante;
import br.com.hikanban.exception.AnuncianteAlreadyExistException;
import br.com.hikanban.exception.AnuncianteNotFoundException;
import br.com.hikanban.exception.EmailAlreadyExistsException;
import br.com.hikanban.repository.anunciante.AnuncianteRepository;
import br.com.hikanban.service.AnuncianteService;

@Service
public class AnuncianteServiceImpl implements AnuncianteService {

	@Autowired
	AnuncianteRepository anuncianteRepository;
		
	@Override
	public AnuncianteResponseDTO getAnunciante(Integer anuncianteId) {
		ModelMapper mapper = new ModelMapper();
		
		Optional<Anunciante> anuncinteOpt = anuncianteRepository.findById(anuncianteId);
		if(!anuncinteOpt.isPresent()) {
			throw new AnuncianteNotFoundException();
		}
		
		Anunciante anunciante = anuncinteOpt.get();
		
		AnuncianteResponseDTO response = mapper.map(anunciante, AnuncianteResponseDTO.class);
		
		return response;
	}
	
	@Override
	public List<AnuncianteResponseDTO> getAllAnunciantes() {
		ModelMapper mapper = new ModelMapper();
		
		List<Anunciante> anunciantes = anuncianteRepository.findAll();
		
		Type listType = new TypeToken<List<AnuncianteResponseDTO>>() {}.getType();
		List<AnuncianteResponseDTO> response = mapper.map(anunciantes, listType);
		
		return response;
	}
	
	

	@Override
	public AnuncianteResponseDTO editAnunciante(Integer anuncianteId, AnuncianteDTO requestDTO) {
		ModelMapper mapper = new ModelMapper();

		Optional<Anunciante> anuncianteOpt = anuncianteRepository.findById(anuncianteId);

		if (!anuncianteOpt.isPresent()) {
			throw new AnuncianteNotFoundException();
		}

		Anunciante anunciante = anuncianteOpt.get();

		if (!StringUtils.equals(requestDTO.getEmail(), anunciante.getEmail())) {
			Boolean anuncianteExists = anuncianteRepository.existsByEmail(requestDTO.getEmail());
			if (anuncianteExists) {
				throw new AnuncianteAlreadyExistException();
			}
		}

		mapper.map(requestDTO, anunciante);

		Anunciante anuncianteSaved = anuncianteRepository.save(anunciante);

		AnuncianteResponseDTO response = mapper.map(anuncianteSaved, AnuncianteResponseDTO.class);

		return response;
	}
	
	@Override
	public void deleteAnunciante(Integer anuncianteId) {
		anuncianteRepository.deleteById(anuncianteId);
	}


}
