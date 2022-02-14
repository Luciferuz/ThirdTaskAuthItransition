package com.antsiferov.thirdtask.controllers;

import com.antsiferov.thirdtask.entity.Users;
import com.antsiferov.thirdtask.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerPage(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@RequestParam String name, @RequestParam String email, @RequestParam String password, Model model) {
        String encryptedPwd = passwordEncoder.encode(password);
        Users user = new Users(name, email, encryptedPwd);
        usersRepository.save(user);
        return "redirect:/home";
    }


}
