package com.goatstickers.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.mindrot.jbcrypt.BCrypt;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "USERS")
public class UserEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID Id;

    @Column(name = "Name", nullable = false, length = 100)
    private String Name;

    @Column(name = "Email", nullable = false, length = 150, unique = true)
    private String Email;

    @Column(name = "Password", nullable = false)
    @JsonIgnore
    private String Password;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    private java.time.LocalDateTime CreatedAt;

    @Column(name = "UpdatedAt")
    private java.time.LocalDateTime UpdatedAt;

    public UserEntity(){}

    public UserEntity(String name, String email, String password, String plainPassword){
        this.Name = name;
        this.Email = email;
        this.Password = password;
        this.setPassword(plainPassword);
        this.CreatedAt = LocalDateTime.now();
    }

    public UUID getId(){
        return Id;
    }

    public void setId(UUID id){
        this.Id = id;
    }

    public String getName(){
        return Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public String getEmail(){
        return Email;
    }

    public void setEmail(String email){
        this.Email = email;
    }

    public String getPassword(){
        return Password;
    }

    public void SetPassword(String password){
        this.Password = password;
    }

    public java.time.LocalDateTime getCreatedAt(){
        return CreatedAt;
    }

    public java.time.LocalDateTime getUpdatedAt(){
        return UpdatedAt;
    }

    @PrePersist
    public void prePersist() {
        CreatedAt = LocalDateTime.now();
        UpdatedAt = LocalDateTime.now();
    }


    @PreUpdate
    public void preUpdate() {
        UpdatedAt = LocalDateTime.now();
    }

    public void setPassword(String plainPassword){
        this.Password = BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    public boolean verifyPassword(String plainPassword){
        return BCrypt.checkpw(plainPassword, this.Password);
    }
}