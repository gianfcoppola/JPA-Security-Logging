package com.example.progettoCompleto.demo.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.progettoCompleto.demo.model.Esame;
import com.example.progettoCompleto.demo.repository.EsamiRepository;

@Service
public class EsameServiceImpl implements EsameService{

	private EsamiRepository esamiRepository;
	private static final Logger logDB = LogManager.getLogger("db-log");
	
	public EsameServiceImpl(EsamiRepository esamiRepository) {
		super();
		this.esamiRepository = esamiRepository;
	}

	@Override
	public Esame saveEsame(Esame esame) {
		// TODO Auto-generated method stub
		logDB.info("Insert into DB new exam of student {}", esame.getIdStudente() );
		return esamiRepository.save(esame);
	}

	@Override
	public List<Esame> getAllEsami() {
		// TODO Auto-generated method stub
		logDB.info("Select query in order to retrieve information about all exams from DB");
		return esamiRepository.findAll();
	}

	@Override
	public Esame getEsameById(int id) {
		// TODO Auto-generated method stub
		logDB.info("Select query in order to retrieve information about exam {}", id);
		return esamiRepository.findById(id).get();
	}

	@Override
	public List<Esame> getEsamiStudente(int idStudente) {
		// TODO Auto-generated method stub
		logDB.info("Select query in order to retrieve information about exams of student {}", idStudente);
		return esamiRepository.findAllByIdStudente(idStudente);
		
	}

	@Override
	public List<Esame> getEsamiCorso(int idCorso) {
		// TODO Auto-generated method stub
		logDB.info("Select query in order to retrieve information about exam of course {}", idCorso);
		return esamiRepository.findAllByIdCorso(idCorso);
	}

}
