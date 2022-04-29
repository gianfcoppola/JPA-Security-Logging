package com.example.progettoCompleto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.progettoCompleto.demo.model.Corso;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, Integer>{

}
