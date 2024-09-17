package pl.gozderapatryk.userservice.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class UserDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
}
