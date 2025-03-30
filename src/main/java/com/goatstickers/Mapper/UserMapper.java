package com.goatstickers.Mapper;

import com.goatstickers.Entity.UserEntity;
import com.goatstickers.DTO.UserDTO;

public class UserMapper {

    public static UserDTO toDTO(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new UserDTO(entity.getName(), entity.getEmail());
    }

    public static UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}