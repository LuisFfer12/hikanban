package br.com.hikanban.service.impl;

import java.io.File;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.persistence.EnumType;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.reflect.TypeToken;

import br.com.hikanban.dto.evento.EventoDTO;
import br.com.hikanban.dto.evento.EventoResponseDTO;
import br.com.hikanban.entity.anunciante.Anunciante;
import br.com.hikanban.entity.evento.Evento;
import br.com.hikanban.enums.CategoriaEventoEnum;
import br.com.hikanban.exception.AnuncianteNotFoundException;
import br.com.hikanban.exception.CategoriaDoesntExists;
import br.com.hikanban.exception.EventoAlreadyExistsException;
import br.com.hikanban.exception.EventoNotFoundException;
import br.com.hikanban.exception.FileNotFoundException;
import br.com.hikanban.repository.anunciante.AnuncianteRepository;
import br.com.hikanban.repository.evento.EventoRepository;
import br.com.hikanban.service.EventoService;
import br.com.hikanban.utils.FileUtil;

@Service
public class EventoServiceImpl implements EventoService {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private AnuncianteRepository anuncianteRepository;
	
	private Path fileStorageLocation = null;

	@Override
	public EventoResponseDTO createEvento(EventoDTO requestDTO) {
		ModelMapper mapper = new ModelMapper();
		Evento evento = mapper.map(requestDTO, Evento.class);
		Integer existeEvento = eventoRepository.buscaEnderecoData(requestDTO.getEndereco(), requestDTO.getData());
		
		
		
		if(existeEvento > 0) {
			throw new EventoAlreadyExistsException();
		}
		
		if(EnumUtils.isValidEnum(CategoriaEventoEnum.class, requestDTO.getCategoria())) {
			evento.setCategoria(CategoriaEventoEnum.valueOf(requestDTO.getCategoria()));
		} else {
			throw new CategoriaDoesntExists();
		}
		
		Optional<Anunciante> anuncianteOpt = anuncianteRepository.findById(requestDTO.getAnuncianteId());
		
		if(!anuncianteOpt.isPresent()) {
			 throw new AnuncianteNotFoundException();
		}
		
		evento.setAnunciante(anuncianteOpt.get());
		
		Evento eventoSalvo = eventoRepository.save(evento);
		EventoResponseDTO response = mapper.map(eventoSalvo, EventoResponseDTO.class);
		return response;
	}
	
	@Override
	public EventoResponseDTO getEvento(Integer eventoId) {
		ModelMapper mapper = new ModelMapper();
		Optional<Evento> eventoOpt = eventoRepository.findById(eventoId);
		if (!eventoOpt.isPresent()) {
			throw new EventoNotFoundException();
		}
		Evento evento =  eventoOpt.get();
		EventoResponseDTO response = mapper.map(evento, EventoResponseDTO.class);
		return response;
	}
	
	@Override
	public List<EventoResponseDTO> getAllEventos(){
		ModelMapper mapper = new ModelMapper();
		List<Evento> evento = eventoRepository.findAll();
		Type listType = new TypeToken< List<EventoResponseDTO>>() {}.getType();
		List<EventoResponseDTO> response = mapper.map(evento, listType);
		return response;
	}
	
	@Override
	public EventoResponseDTO editEvento(Integer eventoId, EventoDTO requestDTO) {
		ModelMapper mapper = new ModelMapper();
		Optional<Evento> eventoOpt = eventoRepository.findById(eventoId);
		if (!eventoOpt.isPresent()) {
			throw new EventoNotFoundException();
		}
		Evento evento = eventoOpt.get();
		if (!StringUtils.equals(requestDTO.getNome(), evento.getNome())) {
			Integer eventoExists = eventoRepository.buscaNome(requestDTO.getNome());
			if (eventoExists > 0) {
				throw new EventoAlreadyExistsException();
			}
		}
		mapper.map(requestDTO, evento);
		Evento eventoSaved = eventoRepository.save(evento);
		EventoResponseDTO response = mapper.map(eventoSaved, EventoResponseDTO.class);
		return response;
	}
	
	@Override
	public void deleteEvento(Integer eventoId) {
		Optional<Evento> eventoOpt = eventoRepository.findById(eventoId);
		if (!eventoOpt.isPresent()) {
			throw new EventoNotFoundException();
		} else {
		eventoRepository.deleteById(eventoId);
		}
	}

	@Override
	public EventoResponseDTO uploadBannerEvento(Integer eventoId, MultipartFile banner) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);

		File file = null;
		try {
			file = FileUtil.convert(banner);
		} catch(Exception ignored) {

		}
		
		Optional<Evento> eventoOpt = eventoRepository.findById(eventoId);
		if (!eventoOpt.isPresent()) {
			throw new EventoNotFoundException();
		}
		
		eventoOpt.get().setBanner(file.getName());
		Evento eventoSaved = eventoRepository.save(eventoOpt.get());
		EventoResponseDTO response = mapper.map(eventoSaved, EventoResponseDTO.class);
		
		return response;
	}

	@Override
	public Resource downloadBanner(String filename) {
		Resource resource = loadFileAsResource(filename);
		return resource;
	}
	
	public Resource loadFileAsResource(String fileName) {
        try {
        	this.fileStorageLocation = Paths.get("/usr/src/mymaven/")
                    .toAbsolutePath().normalize();
        	 Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
             Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException();
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException();
        }
    }

}
