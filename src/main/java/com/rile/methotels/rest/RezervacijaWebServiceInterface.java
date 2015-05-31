package com.rile.methotels.rest;

import com.rile.methotels.entities.Rezervacija;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import javax.ws.rs.core.Response;

/**
 *
 * @author Stefan
 */
@Path("/RezervacijaService")
public interface RezervacijaWebServiceInterface {

    @GET
    @Produces({"application/json"})
    public List<Rezervacija> getAll();

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Rezervacija getRezervacija(@PathParam("id") Integer id);

    @POST
    @CommitAfter
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response post(Rezervacija rezervacija);
}
