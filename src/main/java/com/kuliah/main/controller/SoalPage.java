package com.kuliah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Soal;
import com.kuliah.main.repository.PertanyaanRepository;
import com.kuliah.main.repository.SoalRepository;

@Controller
public class SoalPage {
	@Autowired
	SoalRepository soalRepository;
	@Autowired
	PertanyaanRepository pertanyaanRepository;
	
	@GetMapping("/soal/view")
	public String viewIndexSoal(Model model) {
		model.addAttribute("listSoal", soalRepository.findAll());
		model.addAttribute("active", 5);
		return "view_soal";
	}
	
	@GetMapping("/soal/add")
	public String viewAddSoal(Model model) {
		model.addAttribute("soal", new Soal());
		model.addAttribute("listPertanyaan", pertanyaanRepository.findAll());
		
		
		return "add_soal";
	}
	
	@PostMapping("/soal/view")
	public String addSoal(@ModelAttribute Soal soal, Model model) {
		this.soalRepository.save(soal);
		model.addAttribute("listSoal", soalRepository.findAll());
		
		return "redirect:/soal/view";
	}
	
	@GetMapping("/soal/update/{id}")
	public String viewUpdateSoal(@PathVariable String id, Model model) {
		Soal soal = soalRepository.findById(Long.parseLong(id)).orElse(null);
		model.addAttribute("soal", soal);
		model.addAttribute("listPertanyaan", pertanyaanRepository.findAll());
		
		return "add_soal";
	}
	
	@GetMapping("/soal/delete/{id}")
	public String deleteSoal(@PathVariable String id, Model model) {
		this.soalRepository.deleteById(Long.parseLong(id));
		model.addAttribute("listSoal", soalRepository.findAll());
		
		return "redirect:/soal/view";
	}
	
}
