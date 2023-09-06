package com.usermanageent.usermanagement.mapper;

import com.usermanageent.usermanagement.dto.UserDTO;
import com.usermanageent.usermanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class)
public interface UserMapper {

    UserDTO asUserDTO(User user);

    User asUser(UserDTO userDTO);


    // Mapping from UserDTO to User with multiple field ignores
    @Mapping(target = "id", ignore = true) // Ignore the ID field during updates
    @Mapping(target = "userName", ignore = true) // Ignore the userName field during updates
    void updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);
}
