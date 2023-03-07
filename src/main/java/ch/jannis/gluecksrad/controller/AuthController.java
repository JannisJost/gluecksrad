package ch.jannis.gluecksrad.controller;

import ch.jannis.gluecksrad.repositories.UserRepository;
import ch.jannis.gluecksrad.requests.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public boolean authenticateUser(@RequestBody LoginRequest loginRequest, HttpSession session) {
        boolean loginIsValid = loginRequest.getUsername().equals("user") && loginRequest.getPassword().equals("password");
        if (loginIsValid) {
            session.setAttribute("LoggedIn", true);
            System.err.println("Setting attribute");
            System.out.println(session.getAttribute("LoggedIn"));
        }
        return loginIsValid;
    }

    @PostMapping("/logout")
    public boolean logout(HttpSession session) {
        session.setAttribute("LoggedIn", false);
        System.out.println("log out");
        return true;
    }

    @GetMapping("login-status")
    public Boolean getLoginStatus(HttpSession session) {
        if (session.getAttribute("LoggedIn") == null) {
            return false;
        }
        return (Boolean) session.getAttribute("LoggedIn");
    }
}
