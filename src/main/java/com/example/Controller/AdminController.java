package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.Admin;
import com.example.Repo.AdminRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminRepository AdminRepository;

    

    @PostMapping("/register")
    public Admin register(@RequestBody Admin admin) {
        // Encrypt password before saving
        admin.setPassword(admin.getPassword());
        return AdminRepository.save(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Admin admin) {
        Admin existingUser = AdminRepository.findByUsername(admin.getUsername());
        if (existingUser != null && admin.getPassword().matches(existingUser.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/profile/{username}")
    public Admin getProfile(@PathVariable String username) {
        return AdminRepository.findByUsername(username);
    }
}

