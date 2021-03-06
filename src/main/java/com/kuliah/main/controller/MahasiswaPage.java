package com.kuliah.main.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Mahasiswa;
import com.kuliah.main.entity.MataKuliah;
import com.kuliah.main.repository.MahasiswaRepository;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class MahasiswaPage {
	@Autowired 
	MahasiswaRepository mahasiswaRepository;
	
	@GetMapping("/mahasiswa/view")
	public String viewIndexMahasiswa(Model model) {
		model.addAttribute("listMahasiswa", mahasiswaRepository.findAll());
		model.addAttribute("active", 1);
		return "view_mahasiswa";
	}
	
	@GetMapping("/mahasiswa/add")
	public String viewAddMahasiswa(Model model) {
		// buat penampung data mahasiswa di halaman htmlnya
		model.addAttribute("mahasiswa",new Mahasiswa());
		
		return "add_mahasiswa";
	}
	
	@PostMapping("/mahasiswa/view")
	public String addMahasiswa(@ModelAttribute Mahasiswa mahasiswa, Model model) {
		// buat penampung data mahasiswa di halaman htmlnya
		this.mahasiswaRepository.save(mahasiswa);
		model.addAttribute("listMahasiswa", mahasiswaRepository.findAll());
		
		return "redirect:/mahasiswa/view";
	}
	
	@GetMapping("/mahasiswa/update/{id}")
	public String viewUpdateMahasiswa(@PathVariable String id, Model model) {
		Mahasiswa mahasiswa = mahasiswaRepository.findByIdMahasiswa(Long.parseLong(id));
		model.addAttribute("mahasiswa", mahasiswa);
		return "add_mahasiswa";
	}
	
	@GetMapping("/mahasiswa/delete/{id}")
	public String deleteMahasiswa(@PathVariable String id, Model model) {
		this.mahasiswaRepository.deleteById(Long.parseLong(id));
		model.addAttribute("listMahasiswa", mahasiswaRepository.findAll());
		
		return "redirect:/mahasiswa/view";
	}
}
