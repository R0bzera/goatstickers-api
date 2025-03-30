package com.goatstickers.Service;

import com.goatstickers.DTO.UserDTO;
import com.goatstickers.Entity.UserEntity;
import com.goatstickers.Exception.ApiException;
import com.goatstickers.Mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        try{
            UserEntity userEntity = UserMapper.toEntity(userDTO);
            userEntity.persist();
            return UserMapper.toDTO(userEntity);

        }catch (Exception ex){
            throw new ApiException(Response.Status.BAD_REQUEST, "Erro ao criar usuario: " + ex.getMessage());
        }
    }

    public List<UserDTO> getAllUsers() {
        try {
            return UserEntity.listAll().stream()
                    .map(user -> UserMapper.toDTO((UserEntity) user))
                    .collect(Collectors.toList());

        }catch (Exception ex){
            throw new ApiException(Response.Status.BAD_REQUEST, "Erro ao listar usuarios: " + ex.getMessage());
        }
    }

    public UserDTO getUserById(UUID userId) {

        try{
            Optional<UserEntity> userEntity = UserEntity.findByIdOptional(userId);
            return userEntity.map(UserMapper::toDTO).orElse(null);

        }catch (Exception ex){
            throw new ApiException(Response.Status.BAD_REQUEST, "Erro ao obter usuario: " + ex.getMessage());
        }
    }

    @Transactional
    public void deleteUser(UUID userId) {
        try {
            UserEntity user = UserEntity.findById(userId);
            if (user == null){
                throw new ApiException(Response.Status.BAD_REQUEST, "Erro ao obter usuario: ");

            }
            user.delete();

        }catch (Exception ex){
            throw new ApiException(Response.Status.BAD_REQUEST, "Erro ao deletar usuario: " + ex.getMessage());
        }
    }
}