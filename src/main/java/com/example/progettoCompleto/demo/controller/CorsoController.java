package com.example.progettoCompleto.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.progettoCompleto.demo.dto.CorsoDto;
import com.example.progettoCompleto.demo.mapper.CorsoMapper;
import com.example.progettoCompleto.demo.model.Corso;
import com.example.progettoCompleto.demo.service.CorsoService;
 

@RestController
@RequestMapping("/corsi")
public class CorsoController {

	private CorsoService corsoService;
	 
	private static final Logger logApi = LogManager.getLogger("api-log");
	
	
	public CorsoController(CorsoService corsoService) {
		super();
		this.corsoService = corsoService;
	}

	@PostMapping()
	public ResponseEntity<Corso> saveCorso(@RequestBody Corso corso){
		logApi.info("[POST] - Saving new course {}", corso.getNomeCorso());
		return new ResponseEntity<Corso>(corsoService.saveCorso(corso), HttpStatus.CREATED);
	}
	
	// build get all courses REST API
	@GetMapping
	public ResponseEntity<List<CorsoDto>> getAllCorsi(){
		logApi.info("[GET] - Retrieve information about all courses");
		List<CorsoDto> courseList = new ArrayList<CorsoDto>();
		for (Corso c: corsoService.getAllCorsi())
			courseList.add(CorsoMapper.CorsoToDto(c));
		return new ResponseEntity<List<CorsoDto>>(courseList, HttpStatus.OK);
	}
	
	// build get student by id REST API
	// http://localhost:8080/courses/1
	@GetMapping("{id}")
	public ResponseEntity<CorsoDto> getCorsoById(@PathVariable("id") int id){
		logApi.info("[GET] - Retrieve information about course {}", id);
		return new ResponseEntity<CorsoDto>(CorsoMapper.CorsoToDto(corsoService.getCorsoById(id)), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCorso(@PathVariable("id") int id){
		logApi.info("[DELETE] - Delete course with id {}", id);
		// delete employee from DB
		corsoService.deleteCorso(id);
		
		return new ResponseEntity<String>("Corso deleted successfully!", HttpStatus.OK);
	}
	
}
