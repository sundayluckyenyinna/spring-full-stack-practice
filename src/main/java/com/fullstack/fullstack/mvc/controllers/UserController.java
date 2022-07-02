package com.fullstack.fullstack.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fullstack.fullstack.dao.database.JDBCRepository;
import com.fullstack.fullstack.dto.AuthenticationDTO;
import com.fullstack.fullstack.dto.User;

@Controller
public class UserController{

    @Autowired
    private JDBCRepository repository;

    @GetMapping("/home")
    public String goHome( Model model)
    {
        User user = new User()
                              .setEmail("sundayenyinnaluckydeveloper@gmail.com")
                              .setFirstName("Enyinna")
                              .setLastName("Sunday")
                              .setDepartment("Mechanical Engineering");

        model.addAttribute("message", "Thymeleaf and spring is cool");
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/user")
    public String showUser( Model model ){
        User user = repository.getUserByEmail("sundayenyinna@gmail.com");
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/auth")
    public String showAuthenticatedUser( Model model ){
        AuthenticationDTO auth = repository.getAuthenticatedUser("sundaylucky360@yahoo.com");
        System.out.println( auth );
        model.addAttribute("auth", auth);
        return "auth";
    }

    /**
     * A method to show the registration page to the potential new user
     */
    @GetMapping("/registration")
    public String registerNewUser( Model model ){
        // attach an empty user object to the model. This is so that the thymeleaf engine can fill it in.
        model.addAttribute("newUser", new User());
        return "registration";
    }

    /**
     * A method to handle the actual post processing of the user details
     */
    @PostMapping("/registration")
    @ResponseStatus( value = HttpStatus.OK )
    public void saveNewUser(HttpServletRequest request, HttpServletResponse response ){
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        User user = new User().setEmail(email).setFirstName(firstName);
        System.out.println( user.toString() );
        // return "redirect:/home";
    }

}