package io.danielegradassai.service;

import io.danielegradassai.dto.role.RoleOutputDto;
import io.danielegradassai.dto.user.*;

import java.util.List;

public interface UserService {
    AuthenticationDto login(LoginUserDto userDto);

    UserOutputDto signIn(RegistrationUserDto registrationUserDto);

    List<RoleOutputDto> findRolesById(Long id);

    List<UserOutputDto> findAll();

    List<UserOutputDto> findAllStaff();

    void deleteById(Long id);


    UserOutputDto getUser(Long userId);


}
