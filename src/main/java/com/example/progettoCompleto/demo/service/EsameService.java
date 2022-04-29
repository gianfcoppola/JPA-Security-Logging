package com.example.progettoCompleto.demo.service;

import java.util.List;

import com.example.progettoCompleto.demo.model.Esame;

public interface EsameService {

	Esame saveEsame(Esame esame);
	List<Esame> getAllEsami();
	Esame getEsameById(int id);
	List<Esame> getEsamiStudente(int idStudente);
	List<Esame> getEsamiCorso(int idCorso);
}
