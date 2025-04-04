package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.LoginDto;
import com.airbnb.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService{

    private AppUserRepository appUserRepository;
    private JWTService jwtService;


    public AppUserServiceImpl(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AppUserDto addUser(AppUserDto appUserDto) {
        AppUser savedData = appUserRepository.save(dtoToEntity(appUserDto));
        return entityToDto(savedData);
    }

    @Override
    public String  verifyLogin(LoginDto loginDto) {

        Optional<AppUser> byUsername = appUserRepository.findByUsername(loginDto.getUsername());
        if(byUsername.isPresent()){
            AppUser appUser = byUsername.get();
            if(BCrypt.checkpw(loginDto.getPassword(),appUser.getPassword())){
                String token = jwtService.generateToken(appUser);
                return token;
            }
        }
        return null;


    }

    @Override
    public List<AppUserDto> getUsers() {
        List<AppUser> all = appUserRepository.findAll();
        List<AppUserDto> listOfUsers = all.stream().map(e -> entityToDto(e)).toList();
        return listOfUsers;
    }

    @Override
    public AppUserDto updateUser(AppUserDto appUserDto, long userId) {
        Optional<AppUser> byId = appUserRepository.findById(userId);
        if (byId.isPresent()){
            AppUser appUser = byId.get();
            appUser.setName(appUserDto.getName());
            appUser.setUsername(appUserDto.getUsername());
            appUser.setEmail(appUserDto.getEmail());
            appUser.setPassword(BCrypt.hashpw(appUserDto.getPassword(),BCrypt.gensalt(10)));
            appUser.setRole(appUserDto.getRole());
            AppUser updatedData = appUserRepository.save(appUser);
            return entityToDto(updatedData);
        }
        return null;
    }

    AppUser dtoToEntity(AppUserDto dto){
       AppUser entity = new AppUser();
       entity.setName(dto.getName());
       entity.setUsername(dto.getUsername());
       entity.setEmail(dto.getEmail());
       entity.setPassword(dto.getPassword());
       entity.setRole(dto.getRole());
       return entity;
    }

    AppUserDto entityToDto(AppUser entity){
        AppUserDto dto = new AppUserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        return dto;
    }
}
