package com.goatstickers.Controller.Product;

import com.goatstickers.DTO.Product.CreateProductDTO;
import com.goatstickers.DTO.Product.ProductDTO;
import com.goatstickers.DTO.Product.UpdateProductDTO;
import com.goatstickers.Service.Product.ProductService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Product")
@ApplicationScoped
public class ProductController {

    @Inject
    ProductService productService;

    @POST
    @Path("/create")
    //@RolesAllowed("admin")
    public Response createProduct(CreateProductDTO dto) {
        ProductDTO product = productService.createProduct(dto);
        return Response.status(Response.Status.CREATED).entity(product).build();
    }

    @GET
    public Response listAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") UUID id) {
        ProductDTO product = productService.getProductById(id);
        return Response.ok(product).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") UUID id, UpdateProductDTO dto) {
        ProductDTO updatedProduct = productService.updateProduct(id, dto);
        return Response.ok(updatedProduct).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") UUID id) {
        productService.deleteProduct(id);
        return Response.noContent().build();
    }
}