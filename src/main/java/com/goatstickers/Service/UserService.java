package com.goatstickers.Service;

import com.goatstickers.DTO.UserDTO;
import com.goatstickers.Entity.UserEntity;
import com.goatstickers.Mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = UserMapper.toEntity(userDTO);
        userEntity.persist();
        return UserMapper.toDTO(userEntity);
    }

    public List<UserDTO> getAllUsers() {
        return UserEntity.listAll().stream()
                .map(user -> UserMapper.toDTO((UserEntity) user))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(UUID userId) {

        Optional<UserEntity> userEntity = UserEntity.findByIdOptional(userId);
        return userEntity.map(UserMapper::toDTO).orElse(null);
    }

    @Transactional
    public void deleteUser(UUID userId) {
        UserEntity user = UserEntity.findById(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        user.delete();
    }
}