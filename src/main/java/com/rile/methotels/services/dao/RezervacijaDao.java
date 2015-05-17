package com.rile.methotels.services.dao;

import com.rile.methotels.entities.Rezervacija;
import java.util.List;

/**
 *
 * @author Stefan
 */
public interface RezervacijaDao extends GenericDao<Rezervacija> {
    
    public abstract List<Rezervacija> getListByIme(String ime);
    public abstract List<Rezervacija> loadAllRezervacijaFromTo(int from);
    public abstract int getTotalRezervacija();

}
