package com.example.progettoCompleto.demo.mapper;

import org.mapstruct.Mapper;

import com.example.progettoCompleto.demo.dto.CorsoDto;
import com.example.progettoCompleto.demo.exception.ResourceNotFoundException;
import com.example.progettoCompleto.demo.model.Corso;

@Mapper
//public interface CorsoMapper {
public class CorsoMapper{
	
	public static CorsoDto CorsoToDto(Corso corso) {
		// TODO Auto-generated method stub
		if(corso == null)
			throw new ResourceNotFoundException("Corso");
		
		CorsoDto corsoDto = new CorsoDto();
		
		corsoDto.setId(corso.getId());
		corsoDto.setNomeCorso(corso.getNomeCorso());
		
		return corsoDto;
	}
}
