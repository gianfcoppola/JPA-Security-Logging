package com.example.progettoCompleto.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.progettoCompleto.demo.model.Studente;
import com.example.progettoCompleto.demo.repository.StudentRepository;


@Service
public class UserPrincipalDetailsService implements UserDetailsService {

	private StudentRepository studentRepository;
	
	public UserPrincipalDetailsService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Studente studente = studentRepository.findByUsername(username);
		return new UserPrincipal(studente);
	}

	
}