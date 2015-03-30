package com.rile.methotels.pages;

import com.rile.methotels.entities.Soba;
import java.util.ArrayList;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;


/**
 *
 * @author Stefan
 */
public class DodavanjeSoba {
   
    @Property
    private Soba soba;
    
    @Inject
    private Session session;
    
    @Property
    private ArrayList<Soba> sobe;
    
    void onActivate() {
        if (sobe == null) {
            sobe = new ArrayList<Soba>();
        }
        sobe = (ArrayList<Soba>) session.createCriteria(Soba.class).list();
    }
    
    @CommitAfter
    Object onSuccess() {
        session.persist(soba);
        return this;
    }
    
}
