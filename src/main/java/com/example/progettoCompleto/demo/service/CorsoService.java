package com.example.progettoCompleto.demo.service;

import java.util.List;

import com.example.progettoCompleto.demo.model.Corso;

public interface CorsoService {

	Corso saveCorso(Corso corso);
	List<Corso> getAllCorsi();
	Corso getCorsoById(int id);
	void deleteCorso(int id);
}
