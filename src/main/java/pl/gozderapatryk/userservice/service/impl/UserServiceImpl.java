package pl.gozderapatryk.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gozderapatryk.userservice.dto.UserDto;
import pl.gozderapatryk.userservice.dto.request.CreateUserDto;
import pl.gozderapatryk.userservice.exception.BusinessValidationException;
import pl.gozderapatryk.userservice.exception.UserAlreadyExistsException;
import pl.gozderapatryk.userservice.mapper.UserMapper;
import pl.gozderapatryk.userservice.repository.UserRepository;
import pl.gozderapatryk.userservice.service.UserService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto createUser(CreateUserDto createUserDto) {

        if(userRepository.existsByEmail(createUserDto.getEmail())) {
            throw new UserAlreadyExistsException(String.format("user with the following email %s already exists", createUserDto.getEmail()));
        }

        if (!createUserDto.getEmail().equals(createUserDto.getEmailConfirmation())) {
            throw new BusinessValidationException("password and password confirmation must be the same");
        }

        if (!createUserDto.getPassword().equals(createUserDto.getPasswordConfirmation())) {
            throw new BusinessValidationException("email and email confirmation must be the same");
        }

        var userToSave = userMapper.mapCreateUserDtoToUserEntity(createUserDto);
        userToSave.setActive(false);
        if(Objects.isNull(createUserDto.getUsername())) {
            userToSave.setUsername(createUserDto.getEmail().split("@")[0]);
        }

        return userMapper.mapUserToUserDto(userRepository.save(userToSave));
    }
}