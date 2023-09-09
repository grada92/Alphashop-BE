package io.danielegradassai.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.danielegradassai.dto.InfoMsg;
import io.danielegradassai.dto.user.AuthenticationDto;
import io.danielegradassai.dto.user.LoginUserDto;
import io.danielegradassai.dto.user.RegistrationUserDto;
import io.danielegradassai.dto.user.UserOutputDto;
import io.danielegradassai.entity.User;
import io.danielegradassai.exception.BindingException;
import io.danielegradassai.exception.NotFoundException;
import io.danielegradassai.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/utenti")
@Log
public class UtentiController
{
    @Autowired
    UserService utentiService;
    @Autowired
    private ResourceBundleMessageSource errMessage;

    @GetMapping()
    public ResponseEntity<List<UserOutputDto>> findAll(){
        return new ResponseEntity<>(utentiService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/sign")
    public ResponseEntity<UserOutputDto> sign(@RequestBody RegistrationUserDto registrationUserDto){
        return new ResponseEntity<>(utentiService.signIn(registrationUserDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDto> login(@RequestBody LoginUserDto loginUserDto){
        return new ResponseEntity<>(utentiService.login(loginUserDto), HttpStatus.OK);
    }

}

