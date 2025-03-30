package com.goatstickers.Mapper;

import com.goatstickers.Entity.UserEntity;
import com.goatstickers.DTO.UserDTO;
import com.goatstickers.Types.Email;

public class UserMapper {

    public static UserDTO toDTO(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new UserDTO(entity.getName(), entity.getEmail().getValue());
    }

    public static UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setName(dto.getName());
        entity.setEmail(new Email(dto.getEmail()));
        entity.setPassword(dto.getPassword());
        return entity;
    }
}