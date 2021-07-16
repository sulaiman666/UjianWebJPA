package com.kuliah.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.AdminUser;
import com.kuliah.main.repository.AdminUserRepository;

@Controller
public class AdminUserPage {
	@Autowired
	AdminUserRepository adminUserRepository;
	
	
	@GetMapping("/adminuser/view")
	public String viewIndexAdminUser(Model model) {
		model.addAttribute("listAdminUser",adminUserRepository.findAll());
		model.addAttribute("active",1);
		model.addAttribute("test","Test Aja");
		
		return "view_adminuser";
	}
	
	
	@GetMapping("/adminuser/add")
	public String viewAddAdminUser(Model model) {
		// buat penampung data adminuser di halaman htmlnya
		model.addAttribute("adminuser",new AdminUser());
		return "add_adminuser";
	}
	
	@PostMapping("/adminuser/view")
	  public String addAdminUser(@ModelAttribute AdminUser adminuser, Model model) {
		// buat penampung data adminuser di halaman htmlnya
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = adminuser.getPassword();
		String encodedPassword = passwordEncoder.encode(plainPassword);
        adminuser.setPassword(encodedPassword);		
		
		this.adminUserRepository.save(adminuser);
		model.addAttribute("listAdminUser",adminUserRepository.findAll());
		
		
		return "redirect:/adminuser/view";
	}
	
	
	@GetMapping("/adminuser/update/{id}")
	public String viewUpdateAdminUser(@PathVariable String id, Model model) {
		AdminUser adminuser = adminUserRepository.findById(Long.parseLong(id)).orElse(null);
		// buat penampung data adminuser di halaman htmlnya
		model.addAttribute("adminuser",adminuser);
		
		return "add_adminuser";
	}
	
	@GetMapping("/adminuser/delete/{id}")
	public String deleteAdminUser(@PathVariable String id, Model model) {
		this.adminUserRepository.deleteById(Long.parseLong(id));
		model.addAttribute("listAdminUser",adminUserRepository.findAll());
		
		return "redirect:/adminuser/view";
	}

}
