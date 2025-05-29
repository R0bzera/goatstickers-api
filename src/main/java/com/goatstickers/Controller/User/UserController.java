package com.goatstickers.Controller.User;

import com.goatstickers.DTO.User.LoginResponseDTO;
import com.goatstickers.DTO.User.LoginUserDTO;
import com.goatstickers.DTO.User.UserDTO;
import com.goatstickers.Exception.ApiException;
import com.goatstickers.Service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "User")
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Path("/create")
    public Response createUser(UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    @POST
    @Path("/login")
    public Response login(LoginUserDTO userdto) {
        try {
            LoginResponseDTO response = userService.authenticate(userdto.email, userdto.password);
            return Response.ok(response).build();

        } catch (ApiException ex) {
            return Response.status(ex.getStatus())
                    .entity(Map.of("error", ex.getError()))
                    .build();
        }
    }

    @GET
    @RolesAllowed("user")
    @Path("/read")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @RolesAllowed("user")
    @Path("/{id}")
    public Response getUserById(@PathParam("id") UUID id) {
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(userDTO).build();
    }

    @DELETE
    @RolesAllowed("user")
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id") UUID userId) {
        try {
            userService.deleteUser(userId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
    }
}