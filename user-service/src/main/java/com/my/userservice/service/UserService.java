package com.my.userservice.service;

import com.my.userservice.dao.entity.UserEntity;
import com.my.userservice.dao.repository.UserRepository;
import com.my.userservice.dto.UserDto;
import com.my.userservice.exception.UserNotFoundException;
import com.my.userservice.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserDto findById(UUID id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found " + id));

        return userMapper.toUserDto(userEntity);
    }

    public UserDto findByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found " + username));

        return userMapper.toUserDto(userEntity);
    }

    public List<UserDto> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();

        return userEntities.stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    public UserDto create(UserDto userDto) {

        UserEntity userEntity = userMapper.toUserEntity(userDto);
        userEntity = userRepository.save(userEntity);
        return userMapper.toUserDto(userEntity);

    }

    public UserDto update(UserDto userDto) {
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        userEntity = userRepository.save(userEntity);
        return userMapper.toUserDto(userEntity);
    }

    public String delete(UUID id) {
        userRepository.deleteById(id);
        return "User deleted";
    }


}
