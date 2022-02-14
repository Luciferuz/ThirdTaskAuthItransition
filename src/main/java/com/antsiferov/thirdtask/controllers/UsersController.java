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

import java.util.ArrayList;

@Controller
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String table_users(Model model) {
        Iterable<Users> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/users/block")
    public String blockUser(@RequestParam(value = "id1") ArrayList<Integer> id1, Model model) {
        for (Integer element : id1) {
            Users user = usersRepository.findById(element).orElseThrow();
            user.setStatus("Blocked");
            user.setRole("ROLE_BLOCKED");
            usersRepository.save(user);
        }
        return "redirect:/users";
    }

    @PostMapping("/users/unblock")
    public String unblockUser(@RequestParam(value = "id2") ArrayList<Integer> id2, Model model) {
        for (Integer element : id2) {
            Users user = usersRepository.findById(element).orElseThrow();
            user.setStatus("Good");
            user.setRole("ROLE_USER");
            usersRepository.save(user);
        }
        return "redirect:/users";
    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam(value = "id3") ArrayList<Integer> id3, Model model) {
        for (Integer element : id3) {
            Users user = usersRepository.findById(element).orElseThrow();
            usersRepository.delete(user);
        }
        return "redirect:/users";
    }

}