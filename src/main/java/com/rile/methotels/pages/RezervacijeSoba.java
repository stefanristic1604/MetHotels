package com.rile.methotels.pages;

import com.rile.methotels.entities.Korisnik;
import com.rile.methotels.entities.Rezervacija;
import com.rile.methotels.entities.Role;
import com.rile.methotels.entities.Soba;
import com.rile.methotels.services.dao.RezervacijaDao;
import com.rile.methotels.services.dao.SobaDao;
import com.rile.methotels.services.security.ProtectedPage;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Stefan
 */
@ProtectedPage
@RolesAllowed(value={"Admin", "Sluzbenik", "Korisnik"})
public class RezervacijeSoba {
    
    @Property
    private Rezervacija rezervacija;
    
    @Property 
    private Rezervacija oneRezervacija;
    
    @Inject
    private SobaDao sobaDao;
    
    @Property 
    private Soba soba;
    
    @Inject
    private RezervacijaDao rezervacijaDao;
    
    @Property
    private List<Soba> sobe;
    
    @Property
    private List<Rezervacija> rezervacije;
    
    @SessionState
    private Korisnik loggedInKorisnik;
    
    public ValueEncoder getEncoder() {
        return new ValueEncoder<Soba>() {

            @Override
            public String toClient(Soba v) {
                return String.valueOf(v.getId());
            }

            @Override
            public Soba toValue(String string) {
                return sobaDao.getByID(Integer.parseInt(string));
            }
        };
    }
    
    @PageLoaded
    void onPageLoad() {
        rezervacije = rezervacijaDao.loadAll();
        sobe = sobaDao.loadAll();
    }
    
    void onActivate() {}
    
    @CommitAfter
    Object onSuccess() {
        if (rezervacija != null) {
            rezervacije.add(rezervacijaDao.merge(rezervacija));
        }
        return null;
    }
    
    @CommitAfter
    Object onActionFromDelete(int id) {
        rezervacije.remove(rezervacijaDao.delete(id));
        return null;
    }
    
    public boolean hasListSobaAuthorization() {
        return loggedInKorisnik.getRola() == Role.Admin || 
                loggedInKorisnik.getRola() == Role.Sluzbenik;
    }
    
}
