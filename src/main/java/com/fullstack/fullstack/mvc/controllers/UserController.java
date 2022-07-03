package com.fullstack.fullstack.mvc.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.fullstack.dao.database.JDBCRepository;
import com.fullstack.fullstack.dto.AuthenticationDTO;
import com.fullstack.fullstack.dto.ConcreteDTO;
import com.fullstack.fullstack.dto.User;
import com.fullstack.fullstack.validators.UniqueConcreteValidator;

@Controller
public class UserController{

    @Autowired
    private JDBCRepository repository;

    @Autowired
    private UniqueConcreteValidator uniqueConcreteValidator;

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
        AuthenticationDTO auth = repository.getAuthenticatedUser("sundayluckyenyinna@gmail.com");
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

    @GetMapping("/register-auth")
    public String registerAuth(Model model){
        ConcreteDTO auth = new ConcreteDTO();
        model.addAttribute("auth", auth);
        return "register-auth";
    }

    @PostMapping("/register-auth")
    public String handleRegisterAuth( @Valid @ModelAttribute("auth") ConcreteDTO auth, BindingResult br ){

        System.out.println( auth );
        uniqueConcreteValidator.validate(auth, br);
        if( br.hasErrors() ){
            return "register-auth";
        }
        // save the new user to datastore
        AuthenticationDTO a = new AuthenticationDTO();
        a.setUserId(auth.getUserId());
        a.setUsername(auth.getUsername());
        a.setPassword(auth.getPassword());
        a.setRoles(auth.getRoles());
        a.setAuthorities(auth.getAuthorities());
        repository.saveNewUser(a);
        return "redirect:/home";
    }

    @GetMapping("/all-auths")
    public String getAllAuths(Model model){
        List<AuthenticationDTO> dtos = this.repository.getAllAuthenticationDTOs();
        model.addAttribute("list", dtos);
        return "all-auth";
    }

    @PostMapping("/fileUpload")
    public String uploadMyFile(@RequestParam("myFile") MultipartFile file){

        if( !file.isEmpty() ){
            String fileName = "s" + file.getOriginalFilename();
            System.out.println( fileName );
            File newFile = new File( fileName );
            try{
                byte[] bytes = file.getBytes();
                Files.write( newFile.toPath(), bytes);
            }catch( Exception exception ){
                System.out.println( exception.getMessage() );
                System.out.println("Could not upload file");
            }
        }

        return "redirect:/all-auths";
    }

    //Handling errors
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(){
        return "error";
    }

}