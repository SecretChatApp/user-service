package pl.gozderapatryk.userservice.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.gozderapatryk.userservice.dto.UserDto;
import pl.gozderapatryk.userservice.dto.request.CreateUserDto;
import pl.gozderapatryk.userservice.model.UserEntity;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper {
    UserDto mapUserToUserDto(UserEntity userEntity);
    UserEntity mapCreateUserDtoToUserEntity(CreateUserDto createUserDto);
}
