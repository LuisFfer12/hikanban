package br.com.hikanban.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hikanban.dto.anunciante.AnuncianteDTO;
import br.com.hikanban.dto.anunciante.AnuncianteResponseDTO;
import br.com.hikanban.service.AnuncianteService;

@RestController
@RequestMapping("/anunciante")
@SuppressWarnings("rawtypes")
public class AnuncianteRestController {

	@Autowired
	AnuncianteService anuncianteService;
		
	@PutMapping("/{anuncianteId}")
	public ResponseEntity editAnunciante(@PathVariable("anuncianteId") Integer anuncianteId, @RequestBody AnuncianteDTO requestDTO) {
		AnuncianteResponseDTO response = anuncianteService.editAnunciante(anuncianteId, requestDTO);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{anuncianteId}")
	public ResponseEntity getAnunciante(@PathVariable("anuncianteId") Integer anuncianteId) {
		AnuncianteResponseDTO response = anuncianteService.getAnunciante(anuncianteId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity getAllAnunciantes() {
		List<AnuncianteResponseDTO> response = anuncianteService.getAllAnunciantes();
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{anuncianteId}")
	public void deleteAnunciante(@PathVariable("anuncianteId") Integer anuncianteId) {
		anuncianteService.deleteAnunciante(anuncianteId);
	}
	
	
}
