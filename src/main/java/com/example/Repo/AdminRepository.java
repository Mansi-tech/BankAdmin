package com.example.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Model.Admin;
public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByUsername(String username);
}