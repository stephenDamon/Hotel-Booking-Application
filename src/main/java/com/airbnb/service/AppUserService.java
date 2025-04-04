package com.airbnb.service;

import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.LoginDto;

import java.util.List;

public interface AppUserService {
    AppUserDto addUser(AppUserDto appUserDto);

    String verifyLogin(LoginDto loginDto);

    List<AppUserDto> getUsers();

    AppUserDto updateUser(AppUserDto appUserDto, long userId);
}
