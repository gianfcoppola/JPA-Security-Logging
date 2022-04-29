package com.example.progettoCompleto.demo.mapper;

import org.mapstruct.Mapper;

import com.example.progettoCompleto.demo.dto.StudentDto;
import com.example.progettoCompleto.demo.exception.ResourceNotFoundException;
import com.example.progettoCompleto.demo.model.Studente;

@Mapper
//public interface StudentMapper {
public class StudentMapper{
	
	public static StudentDto StudentToDto(Studente studente) {
		// TODO Auto-generated method stub
		if(studente == null)
			throw new ResourceNotFoundException("Studente");
		
		StudentDto studentDto = new StudentDto();
		
		studentDto.setId(studente.getId());
		studentDto.setNomeCognome(studente.getNomeCognome());
		studentDto.setCdl(studente.getCdl());
		
		return studentDto;
	}
}
