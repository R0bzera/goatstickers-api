package com.goatstickers.Controller;

import com.goatstickers.DTO.UserDTO;
import com.goatstickers.Service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Path("/create")
    public Response createUser(UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    @GET
    @Path("/read")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") UUID id) {
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(userDTO).build();
    }

    @DELETE
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