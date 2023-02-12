package pl.gozderapatryk.userservice.service;

import pl.gozderapatryk.userservice.dto.UserDto;
import pl.gozderapatryk.userservice.dto.request.CreateUserDto;

public interface UserService {
    UserDto createUser(CreateUserDto createUserDto);
}
