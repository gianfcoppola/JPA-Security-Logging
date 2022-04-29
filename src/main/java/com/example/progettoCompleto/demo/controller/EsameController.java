package com.example.progettoCompleto.demo.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.progettoCompleto.demo.model.Esame;
import com.example.progettoCompleto.demo.service.EsameService;

@RestController
@RequestMapping("/esami")
public class EsameController {
	
	private EsameService esameService;
	private static final Logger logApi = LogManager.getLogger("api-log");

	public EsameController(EsameService esameService) {
		super();
		this.esameService = esameService;
	}
	
	@PostMapping()
	public ResponseEntity<Esame> saveEsame(@RequestBody Esame esame){
		logApi.info("[POST] - Saving new exam of student {}", esame.getIdStudente());
		return new ResponseEntity<Esame>(esameService.saveEsame(esame), HttpStatus.CREATED);
	}
	
	// build get all courses REST API
	@GetMapping
	public ResponseEntity<List<Esame>> getAllEsami(){
		logApi.info("[GET] - Retrieve information about all exams");
		return new ResponseEntity<List<Esame>>(esameService.getAllEsami(), HttpStatus.OK);
	}
	
	// build get student by id REST API
	// http://localhost:8080/courses/1
	@GetMapping("{id}")
	public ResponseEntity<Esame> getEsameById(@PathVariable("id") int id){
		logApi.info("[GET] - Retrieve information about exam with id {}", id);
		return new ResponseEntity<Esame>(esameService.getEsameById(id), HttpStatus.OK);
	}
	
	@GetMapping("/student/{idStudente}")
	public ResponseEntity<List<Esame>> getEsameByIdStudente(@PathVariable("idStudente") int idStudente){
		logApi.info("[GET] - Retrieve information about exams of student {}", idStudente);
		return new ResponseEntity<List<Esame>>(esameService.getEsamiStudente(idStudente), HttpStatus.OK);
	}
	
	@GetMapping("/course/{idCorso}")
	public ResponseEntity<List<Esame>> getEsameByIdCorso(@PathVariable("idCorso") int idCorso){
		logApi.info("[GET] - Retrieve information about exams of course {}", idCorso);
		return new ResponseEntity<List<Esame>>(esameService.getEsamiCorso(idCorso), HttpStatus.OK);
	}


}
