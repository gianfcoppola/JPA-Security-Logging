package com.example.progettoCompleto.demo.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.progettoCompleto.demo.exception.ResourceNotFoundException;
import com.example.progettoCompleto.demo.model.Studente;
import com.example.progettoCompleto.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	private static final Logger logDB = LogManager.getLogger("db-log");
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public Studente saveStudent(Studente studente) {
		// TODO Auto-generated method stub
		logDB.info("Insert into DB new student {}", studente.getNomeCognome());
		return studentRepository.save(studente);
	}

	@Override
	public List<Studente> getAllStudents() {
		// TODO Auto-generated method stub
		logDB.info("Select query in order to retrieve information about all students from DB ");
		return studentRepository.findAll();
	}

	@Override
	public Studente getStudenteById(int id) {
		// TODO Auto-generated method stub
		logDB.info("Select query in order to retrieve information student {} from DB", id);
		Optional<Studente> studente = studentRepository.findById(id);
		if(studente.isPresent())
			return studente.get();
		else 
			throw new ResourceNotFoundException("Studente", "id", id);
	
	}

	@Override
	public Studente updateStudente(Studente studente, int id) {
		// TODO Auto-generated method stub
		logDB.info("Set query in order to update information student {} into DB", id);
		Studente s = studentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Studente", "Id", id)); 
		
		s.setNomeCognome(studente.getNomeCognome());
		s.setUsername(studente.getUsername());
		s.setPassword(studente.getPassword());
		s.setCdl(studente.getCdl());
		s.setId(studente.getId());
		// save existing employee to DB
		studentRepository.save(s);
		return s;
	}

	@Override
	public void deleteStudente(int id) {
		// TODO Auto-generated method stub
		logDB.info("Delete query in order to delete student {} from DB", id);
		studentRepository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("Studente", "Id", id));
		studentRepository.deleteById(id);
	}
	
	
}
