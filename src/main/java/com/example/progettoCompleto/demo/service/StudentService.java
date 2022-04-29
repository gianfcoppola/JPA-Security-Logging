package com.example.progettoCompleto.demo.service;

import java.util.List;

import com.example.progettoCompleto.demo.model.Studente;

public interface StudentService {

	Studente saveStudent(Studente studente);
	List<Studente> getAllStudents();
	Studente getStudenteById(int id);
	Studente updateStudente(Studente employee, int id);
	void deleteStudente(int id);
}
