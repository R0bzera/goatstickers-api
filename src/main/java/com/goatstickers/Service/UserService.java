package com.goatstickers.Service;

import com.goatstickers.DTO.User.LoginResponseDTO;
import com.goatstickers.DTO.User.LoginUserDTO;
import com.goatstickers.DTO.User.UserDTO;
import com.goatstickers.Entity.UserEntity;
import com.goatstickers.Exception.ApiException;
import com.goatstickers.Mapper.UserMapper;
import com.goatstickers.Repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;
import io.smallrye.jwt.build.Jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

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

    public LoginResponseDTO authenticate(String emailStr, String password) {
        try {
            Optional<UserEntity> optionalUser = userRepository.findByEmail(emailStr);

            if (optionalUser.isEmpty()) {
                throw new ApiException(Response.Status.UNAUTHORIZED, "Email ou senha inválidos");
            }

            UserEntity user = optionalUser.get();

            boolean senhaCorreta = BCrypt.checkpw(password, user.getPassword());

            if (!senhaCorreta) {
                throw new ApiException(Response.Status.UNAUTHORIZED, "Email ou senha inválidos");
            }

            String token = Jwt.claims()
                    .issuer("quarkus-jwt")
                    .upn(user.getEmail().toString())
                    .groups(Set.of("user"))
                    .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                    .sign();

            LoginUserDTO userDTO = UserMapper.toLoginDTO(user);

            return new LoginResponseDTO(token, userDTO);

        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException(Response.Status.BAD_REQUEST, "Erro ao autenticar usuário: " + ex.getMessage());
        }
    }
}