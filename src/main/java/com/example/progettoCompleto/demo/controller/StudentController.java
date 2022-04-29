package com.example.progettoCompleto.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.progettoCompleto.demo.dto.StudentDto;
import com.example.progettoCompleto.demo.mapper.StudentMapper;
import com.example.progettoCompleto.demo.model.Studente;
import com.example.progettoCompleto.demo.service.StudentService;

@RestController
@RequestMapping("/studenti")
public class StudentController {
 
	private StudentService studentService;
	private static final Logger logApi = LogManager.getLogger("api-log");

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@PostMapping()
	public ResponseEntity<Studente> saveStudente(@RequestBody Studente studente){
		logApi.info("[POST] - Creating new student {}", studente.getNomeCognome());
		studente.setPassword(new BCryptPasswordEncoder().encode(studente.getPassword()));
		return new ResponseEntity<Studente>(studentService.saveStudent(studente), HttpStatus.CREATED);
	}
	
	// build get all students REST API
	@GetMapping
	public ResponseEntity<List<StudentDto>> getAllStudents(){
		logApi.info("[GET] - Retrieve information about all students");
		
		List<StudentDto> studentList = new ArrayList<StudentDto>();
		for (Studente s: studentService.getAllStudents())
			studentList.add(StudentMapper.StudentToDto(s));
		return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.OK);
	}
	
	// build get student by id REST API
	// http://localhost:8080/students/1
	@GetMapping("{id}")
	public ResponseEntity<StudentDto> getStudenteById(@PathVariable("id") int id){
		logApi.info("[GET] - Retrieve information about student with id {}", id);
		return new ResponseEntity<StudentDto>(StudentMapper.StudentToDto(studentService.getStudenteById(id)), HttpStatus.OK);
	}
	
	// build update employee REST API
	// http://localhost:8080/students/1
	@PutMapping("{id}")
	public ResponseEntity<Studente> updateStudente(@PathVariable("id") int id
												  ,@RequestBody Studente studente){
		logApi.info("[PUT] - Update student with id {}", id);
		studente.setPassword(new BCryptPasswordEncoder().encode(studente.getPassword()));
		return new ResponseEntity<Studente>(studentService.updateStudente(studente, id), HttpStatus.OK);
	}
	
	// build delete employee REST API
	// http://localhost:8080/students/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudente(@PathVariable("id") int id){
		logApi.info("[DELETE] - Delete student with id {}", id);
		// delete employee from DB
		studentService.deleteStudente(id);
		
		return new ResponseEntity<String>("Student deleted successfully!.", HttpStatus.OK);
	}
	
}
