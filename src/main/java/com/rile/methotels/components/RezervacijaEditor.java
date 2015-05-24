package com.rile.methotels.components;

import com.rile.methotels.entities.Rezervacija;
import com.rile.methotels.services.dao.RezervacijaDao;
import com.rile.methotels.services.dao.RezervacijaDaoType;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Stefan
 */
public class RezervacijaEditor extends GenericEditor<Rezervacija, RezervacijaDao> {

    @Inject
    @RezervacijaDaoType
    private RezervacijaDao rezervacijaDao;
    
    @Override
    protected RezervacijaDao getGenericDao() {
        return rezervacijaDao;
    }
    
}
