package com.rile.methotels.rest;

import com.rile.methotels.entities.Rezervacija;
import com.rile.methotels.services.dao.RezervacijaDao;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Stefan
 */
public class RezervacijaWebService implements RezervacijaWebServiceInterface {

    @Inject
    private RezervacijaDao rezervacijaDao;
    
    @Override
    public List<Rezervacija> getAll() {
        return rezervacijaDao.loadAll();
    }

    @Override
    public Rezervacija getRezervacija(@PathParam("id")Integer id) {
        return rezervacijaDao.getByID(id);
    }

    @Override
    public Response post(Rezervacija rezervacija) {
        rezervacijaDao.merge(rezervacija);
        return Response.ok().entity(rezervacija).build();
    }
    
}
