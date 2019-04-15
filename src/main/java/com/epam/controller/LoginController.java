package com.epam.controller;

import com.epam.dto.TokenDto;
import com.epam.forms.LoginForm;
import com.epam.service.LoginService;
import com.sun.net.httpserver.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
@PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginForm loginForm,HttpServletResponse response){
    String token=loginService.login(loginForm);
    Cookie cookie=new Cookie("token",token);
    cookie.setMaxAge(24*60*60);
    response.addCookie(cookie);
    Arrays.asList("a","b","c").forEach((String s)->System.out.println(s));
    return ResponseEntity.ok().build();
}
}
