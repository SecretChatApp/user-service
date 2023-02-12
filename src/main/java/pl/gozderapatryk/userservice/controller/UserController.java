package pl.gozderapatryk.userservice.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.gozderapatryk.userservice.dto.UserDto;
import pl.gozderapatryk.userservice.dto.request.CreateUserDto;
import pl.gozderapatryk.userservice.service.impl.UserServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserServiceImpl userService;

    @ApiOperation(value = "Creates a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully"),
            @ApiResponse(code = 409, message = "User with such email already exists"),
            @ApiResponse(code = 422, message = "Create a new user body parameters contains validation errors"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }
}
