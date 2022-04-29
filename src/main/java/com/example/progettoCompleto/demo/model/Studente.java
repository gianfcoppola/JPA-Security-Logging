package com.example.progettoCompleto.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="studenti")
public class Studente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	@Column(name = "nome_cognome")
	private String nomeCognome;
	private String cdl;
	
	@OneToMany(targetEntity = Esame.class, cascade = CascadeType.ALL)
	@JoinColumn(name="id_studente", referencedColumnName = "id")
	private List<Esame> esamiSvolti;
}
