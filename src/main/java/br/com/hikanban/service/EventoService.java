package br.com.hikanban.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.hikanban.dto.evento.EventoDTO;
import br.com.hikanban.dto.evento.EventoResponseDTO;

@Service
public interface EventoService {

	EventoResponseDTO createEvento(EventoDTO requestDTO);
	
	EventoResponseDTO getEvento(Integer eventoId);

	List<EventoResponseDTO> getAllEventos();

	EventoResponseDTO editEvento(Integer eventoId, EventoDTO requestDTO);

	void deleteEvento(Integer eventoId);

	EventoResponseDTO uploadBannerEvento(Integer eventoId, MultipartFile banner);

	Resource downloadBanner(String filename);
}
