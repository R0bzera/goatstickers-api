package com.goatstickers.Repository;

import com.goatstickers.Entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<UserEntity, UUID> {

    @Inject
    EntityManager em;

    public Optional<UserEntity> findByEmail(String emailStr) {
        try {
            return Optional.ofNullable((UserEntity) em
                    .createNativeQuery("SELECT * FROM USERS WHERE Email = ?1", UserEntity.class)
                    .setParameter(1, emailStr)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}