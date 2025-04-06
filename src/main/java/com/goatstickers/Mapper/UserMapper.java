package com.goatstickers.Mapper;

import com.goatstickers.DTO.User.LoginUserDTO;
import com.goatstickers.Entity.UserEntity;
import com.goatstickers.DTO.User.UserDTO;
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

    public static LoginUserDTO toLoginDTO(UserEntity user) {
        LoginUserDTO dto = new LoginUserDTO();
        dto.setEmail(user.getEmail().getValue());
        dto.setName(user.getName());
        return dto;
    }
}