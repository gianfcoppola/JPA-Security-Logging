package com.example.progettoCompleto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.progettoCompleto.demo.model.Studente;

@Repository
public interface StudentRepository extends JpaRepository<Studente, Integer>{

	Studente findByUsername(String username);
}
