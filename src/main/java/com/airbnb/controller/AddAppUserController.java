package com.airbnb.controller;

import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.JWTTokenDto;
import com.airbnb.payload.LoginDto;
import com.airbnb.repository.AppUserRepository;
import com.airbnb.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airbnb/appUser")
public class AddAppUserController {

    private AppUserService appUserService;
    private AppUserRepository appUserRepository;
    private JWTTokenDto jwtTokenDto;

    public AddAppUserController(AppUserService appUserService, AppUserRepository appUserRepository, JWTTokenDto jwtTokenDto) {
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
        this.jwtTokenDto = jwtTokenDto;
    }


    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody AppUserDto appUserDto){

        if (appUserRepository.existsByUsername(appUserDto.getUsername())){
            return new ResponseEntity<>("username already present",HttpStatus.BAD_REQUEST);

        }
        if (appUserRepository.existsByEmail(appUserDto.getEmail())){
            return new ResponseEntity<>("email already present",HttpStatus.BAD_REQUEST);
        }
        appUserDto.setPassword(BCrypt.hashpw(appUserDto.getPassword(),BCrypt.gensalt(10)));
        AppUserDto user = appUserService.addUser(appUserDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(
            @RequestBody AppUserDto appUserDto,
            @RequestParam long userId){
        AppUserDto updatedUser = appUserService.updateUser(appUserDto, userId);
        if (updatedUser!=null){
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }
        return new ResponseEntity<>("invalid userId",HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/login")
    public ResponseEntity<?> verifyLogin(@RequestBody LoginDto loginDto){
        String token = appUserService.verifyLogin(loginDto);
        if (token!=null){
            jwtTokenDto.setToken(token);
            jwtTokenDto.setType("Bearer Token");
            return new ResponseEntity<>(jwtTokenDto,HttpStatus.OK);
        }
        return new ResponseEntity<>("invalid Token",HttpStatus.OK);

    }
    @GetMapping("/getUsers")
    public ResponseEntity<List<AppUserDto>> getUsers(){
        List<AppUserDto> users = appUserService.getUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);

    }

}
