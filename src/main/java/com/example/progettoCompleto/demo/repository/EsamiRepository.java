package com.example.progettoCompleto.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.progettoCompleto.demo.model.Esame;

@Repository
public interface EsamiRepository extends JpaRepository<Esame, Integer> {

	List<Esame> findAllByIdStudente(int idStudente);
	List<Esame> findAllByIdCorso(int idCorso);
	//Esame findByIdStudente(int idStudente);
	//Esame findByIdCorso(int idCorso);
}
