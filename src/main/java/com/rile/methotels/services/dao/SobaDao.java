package com.rile.methotels.services.dao;

import com.rile.methotels.entities.Soba;
import java.util.List;

/**
 *
 * @author Stefan
 */
public interface SobaDao {
    
    public List<Soba> getListSoba();
    public Soba getSobaById(Integer id);
    public void addSoba(Soba soba);
    public void removeSoba(Integer id);
    
}
