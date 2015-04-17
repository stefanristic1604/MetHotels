package com.rile.methotels.services.dao;

import com.rile.methotels.entities.Rezervacija;
import java.util.List;

/**
 *
 * @author Stefan
 */
public interface RezervacijaDao {
    
    public List<Rezervacija> getListRezervacija();
    public Rezervacija getRezervacijaById(Integer id);
    public void addRezervacija(Rezervacija rezervacija);
    public void removeRezervacija(Integer id);
    
}
