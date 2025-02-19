package com.distribuida.authors.rest;

import com.distribuida.authors.db.Author;
import com.distribuida.authors.repo.AuthorRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class AuthorRest {

    @Inject
    AuthorRepository rep;

    //books GET
    @GET
    public List<Author> findAll() {
        return rep.findAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        var au = rep.findById(id);

        return Response.ok(au).build();
    }

    @POST
    public Response create(Author p) {
        rep.persist(p);

        return Response.status(Response.Status.CREATED.getStatusCode(), "author created").build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Author authorObj) {
        Author author = rep.findById(id);
        author.setNombre(authorObj.getNombre());
        author.setApellido(authorObj.getApellido());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        rep.deleteById(id);

        return Response.ok( )
                .build();
    }
}
