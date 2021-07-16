package com.kuliah.main.repository;

import org.springframework.data.repository.CrudRepository;

import com.kuliah.main.entity.Soal;

public interface SoalRepository extends CrudRepository<Soal, Long>{
	
	public Soal findByNamaSoal(String nama);
	//public Soal findByIdSoal(Long id);

}
