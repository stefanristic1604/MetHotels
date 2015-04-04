package com.rile.methotels.pages;

import com.rile.methotels.entities.Rezervacija;
import com.rile.methotels.entities.Soba;
import com.rile.methotels.services.RezervacijaDao;
import com.rile.methotels.services.SobaDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Stefan
 */
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
    
    
    public ValueEncoder getEncoder() {
        return new ValueEncoder<Soba>() {

            @Override
            public String toClient(Soba v) {
                return String.valueOf(v.getId());
            }

            @Override
            public Soba toValue(String string) {
                return sobaDao.getSobaById(Integer.parseInt(string));
            }
        };
    }
    
    void onActivate() {
        if (rezervacije == null) {
            rezervacije = new ArrayList<Rezervacija>();
        }
        rezervacije = rezervacijaDao.getListRezervacija();
        
        if (sobe == null) {
            sobe = new ArrayList<Soba>();
        }
        sobe = sobaDao.getListSoba();
    }
    
    @CommitAfter
    Object onSuccess() {
        rezervacija.setSobaId(soba.getId());
        rezervacijaDao.addRezervacija(rezervacija);
        return this;
    }
    
    @CommitAfter
    Object onActionFromDelete(int id) {
        System.out.println("Remove object " + id);
        rezervacijaDao.removeRezervacija(id);
        return this;
    }
    
}
