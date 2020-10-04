package br.com.hikanban.controller;

import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.hikanban.dto.evento.EventoDTO;
import br.com.hikanban.dto.evento.EventoResponseDTO;
import br.com.hikanban.service.EventoService;

@RestController
@RequestMapping("/eventos")
@SuppressWarnings("rawtypes")
public class EventoRestController {
	
	@Autowired
	private EventoService eventoService;
	
	@PostMapping
	public ResponseEntity createAnuncio(@RequestBody EventoDTO requestDTO) {
		EventoResponseDTO response = eventoService.createEvento(requestDTO);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{eventoId}")
	public ResponseEntity getEvento(@PathVariable("eventoId") Integer eventoId) {
		EventoResponseDTO response = eventoService.getEvento(eventoId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity getAllEventos() {
		List<EventoResponseDTO> response = eventoService.getAllEventos();
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{eventoId}")
	public ResponseEntity editEventos(@PathVariable("eventoId") Integer eventoId, @RequestBody EventoDTO requestDTO) {
		EventoResponseDTO response = eventoService.editEvento(eventoId, requestDTO);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/uploadBanner/{eventoId}")
	public ResponseEntity uploadBannerEvento(@PathVariable("eventoId") Integer eventoId,  @RequestParam("banner") MultipartFile banner) {
		EventoResponseDTO response = eventoService.uploadBannerEvento(eventoId, banner);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/downloadBanner/{filename}")
	public ResponseEntity downloadBanner(@PathVariable("filename") String filename) {
		Resource response = eventoService.downloadBanner(filename);
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		String mimeType = mimeTypesMap.getContentType(filename);
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + response.getFilename() + "\"")
                .body(response);
	}
	
	@DeleteMapping("/{eventoId}")
	public void deleteEvento(@PathVariable("eventoId")Integer eventoId) {
		eventoService.deleteEvento(eventoId);
	}
	
	
	

}
