package com.my.userservice.mapper;

import com.my.userservice.dao.entity.UserEntity;
import com.my.userservice.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toUserEntity(UserDto userDto);

    UserDto toUserDto(UserEntity userEntity);
}
