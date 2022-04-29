package com.example.progettoCompleto.demo.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.progettoCompleto.demo.exception.ResourceNotFoundException;
import com.example.progettoCompleto.demo.model.Corso;
import com.example.progettoCompleto.demo.repository.CorsoRepository;

@Service
public class CorsoServiceImpl implements CorsoService {

	private CorsoRepository corsoRepository;
	private static final Logger logDB = LogManager.getLogger("db-log");
	
	public CorsoServiceImpl(CorsoRepository corsoRepository) {
		super();
		this.corsoRepository = corsoRepository;
	}

	@Override
	public Corso saveCorso(Corso corso) {
		// TODO Auto-generated method stub
		logDB.info("Insert into DB new course {}", corso.getNomeCorso());
		return corsoRepository.save(corso);
	}

	@Override
	public List<Corso> getAllCorsi() {
		// TODO Auto-generated method stub
		logDB.info("Select query in order to retrieve information about all courses from DB");
		return corsoRepository.findAll();
	}

	@Override
	public Corso getCorsoById(int id) {
		// TODO Auto-generated method stub
		logDB.info("Select query in order to retrieve information about course {}", id);
		return corsoRepository.findById(id).get();
	}

	@Override
	public void deleteCorso(int id) {
		// TODO Auto-generated method stub
		logDB.info("Delete query in order to delete course {} from DB", id);
		corsoRepository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("Corso", "Id", id));
		corsoRepository.deleteById(id);
	}
}
