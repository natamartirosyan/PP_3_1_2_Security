package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserServiceImpl userService, RoleServiceImpl roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin")
    public String allUsersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/admin/show")
    public String userById(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getListOfRoles());
        return "new";
    }

    @PostMapping("/admin/addUser")
    public String addUser(@ModelAttribute("user") @Valid User user,
                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.getListOfRoles());
            return "new";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    public String editUserById(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("roles", roleService.getListOfRoles());
        return "edit";
    }

    @PostMapping("/admin/save")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.getListOfRoles());
            return "edit";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.editUserById(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
