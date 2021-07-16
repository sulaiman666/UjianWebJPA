package com.kuliah.main.repository;

import org.springframework.data.repository.CrudRepository;

import com.kuliah.main.entity.AdminUser;
import com.kuliah.main.entity.Mahasiswa;

public interface AdminUserRepository extends CrudRepository<AdminUser, Long> {
	public AdminUser findByUsername(String username);
}
