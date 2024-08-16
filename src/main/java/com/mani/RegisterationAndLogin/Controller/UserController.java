package com.mani.RegisterationAndLogin.Controller;

import java.util.NoSuchElementException;

import com.mani.RegisterationAndLogin.Entity.User;
import com.mani.RegisterationAndLogin.GlobalException.AuthenticationException;
import com.mani.RegisterationAndLogin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userser;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("home");
        return "home";
    }
    @GetMapping("/registerpage")
    public String getregister(Model model) {
        model.addAttribute("registeration", new User());
        return "register";
    }

    @GetMapping("/loginpage")
    public String getlogin(Model model) {
        model.addAttribute("loggedin", new User());
        return "login";
    }

    @PostMapping("/register")
    public String newregister(@ModelAttribute User user, Model model) {

        System.out.println("Attempting to register user: " + user);
        if (isUserAlreadyRegistered(user.getEmail())) {
            model.addAttribute("registrationError", "Email Already Exist please login!!!");
            model.addAttribute("showModel", true);
        } else {
            User registeredUser = userser.register(user);

            if (registeredUser != null) {
                System.out.println("User successfully registered");
                return "login";
            } else {
                model.addAttribute("registrationError", "Error during registration. Please try again.");
                return "register";
            }
        }
        return "register";
    }

    private boolean isUserAlreadyRegistered(String email) {
        boolean isRegistered = userser.isUserAlreadyRegistered(email);
        System.out.println("Checking if user is already registered: " + email + " - Result: " + isRegistered);
        return isRegistered;

    }

    @PostMapping("/login")
    public String newlogin(@ModelAttribute User user, Model model) throws AuthenticationException {
        System.out.println(user);
        try {
            User authenticatedUser = userser.auth(user.getEmail(), user.getPassword());
            model.addAttribute("success", authenticatedUser);
            return welcomePage();
        } catch (NoSuchElementException e) {
            System.out.println("error");
            model.addAttribute("error", "Invalid email or password please try Again!!!");
            model.addAttribute("showmodal", true);
            return "login";
        } catch (AuthenticationException e) {
            throw new AuthenticationException("authentcation failed");
        }
    }
    @GetMapping("/welcome")
    public String welcomePage(){
        return "welcome";
    }
    @GetMapping("/logout")
    public String logout(Model model) {
        return "logout";
    }



}
