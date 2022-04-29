package com.example.progettoCompleto.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="corsi")
public class Corso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nome_corso")
	private String nomeCorso;
	private int cfu;
	
	@OneToMany(targetEntity = Esame.class, cascade = CascadeType.ALL)
	@JoinColumn(name="id_corso", referencedColumnName = "id")
	private List<Esame> esami;
}
