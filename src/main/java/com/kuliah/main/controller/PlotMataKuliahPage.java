package com.kuliah.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuliah.main.entity.LembarPenilaian;
import com.kuliah.main.entity.Pertanyaan;
import com.kuliah.main.entity.PlotMataKuliah;
import com.kuliah.main.entity.Soal;
import com.kuliah.main.entity.UjianHasil;
import com.kuliah.main.repository.DosenRepository;
import com.kuliah.main.repository.MahasiswaRepository;
import com.kuliah.main.repository.MataKuliahRepository;
import com.kuliah.main.repository.PlotMataKuliahRepository;
import com.kuliah.main.repository.SoalRepository;

@Controller
public class PlotMataKuliahPage {
	@Autowired
	PlotMataKuliahRepository plotMatKulRepo;
	@Autowired
	MataKuliahRepository mataKuliahRepo;
	@Autowired
	MahasiswaRepository mahasiswaRepo;
	@Autowired
	DosenRepository dosenRepo;
	@Autowired
	SoalRepository soalRepo;
	
	@GetMapping("/plotmatakuliah/view")
	public String viewIndexPlotMatKul(Model model) {
		model.addAttribute("listPlotMataKuliah", plotMatKulRepo.findAll());
		model.addAttribute("active", 6);
		
		return "view_plotmatakuliah";
	}
	
	@GetMapping("/plotmatakuliah/add")
	public String viewAddPlotMatKul (Model model) {
		model.addAttribute("plotmatakuliah", new PlotMataKuliah());
		model.addAttribute("listMataKuliah", mataKuliahRepo.findAll());
		model.addAttribute("listMahasiswa", mahasiswaRepo.findAll());
		model.addAttribute("listDosen", dosenRepo.findAll());
		model.addAttribute("listSoal", soalRepo.findAll());
		
		return "add_plotmatakuliah";
	}
	
	@PostMapping("/plotmatakuliah/view")
	public String addPlotMatKul(@ModelAttribute PlotMataKuliah plotMatKul, Model model) {
		this.plotMatKulRepo.save(plotMatKul);
		model.addAttribute("listPlotMataKuliah", plotMatKulRepo.findAll());
		
		return "redirect:/plotmatakuliah/view";
	}
	
	@GetMapping("/plotmatakuliah/update/{id}")
	public String viewUpdatePlotMatkul(@PathVariable String id, Model model) {
		PlotMataKuliah plotMatKul = plotMatKulRepo.findByIdPlotMataKuliah(Long.parseLong(id));
		model.addAttribute("plotmatakuliah", plotMatKul);
		model.addAttribute("listMataKuliah", mataKuliahRepo.findAll());
		model.addAttribute("listMahasiswa", mahasiswaRepo.findAll());
		model.addAttribute("listDosen", dosenRepo.findAll());
		model.addAttribute("listSoal", soalRepo.findAll());
		
		return "add_plotmatakuliah";
	}
	
	@GetMapping("/plotmatakuliah/delete/{id}")
	public String deletePlotMatKul(@PathVariable String id, Model model) {
		this.plotMatKulRepo.deleteById(Long.parseLong(id));
		model.addAttribute("listPlotMataKuliah", plotMatKulRepo.findAll());
		
		return "redirect:/plotmatakuliah/view";
	}
	
	@GetMapping("/plotmatakuliah/ujian/{id}")
	public String viewUjianPlotMatKul(@PathVariable String id, Model model) {
		model.addAttribute("ujian", soalRepo.findById(Long.parseLong(id)));
		return "view_ujian";
	}
}
