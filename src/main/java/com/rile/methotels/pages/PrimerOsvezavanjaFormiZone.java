package com.rile.methotels.pages;

import com.rile.methotels.entities.Rezervacija;
import com.rile.methotels.services.dao.RezervacijaDao;
import java.util.List;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

/**
 *
 * @author Stefan
 */
public class PrimerOsvezavanjaFormiZone {

    @Inject
    private RezervacijaDao rezervacijaDao;
    @Property
    @Persist
    private Rezervacija rezervacija;
    @Property
    private Rezervacija oneRezervacija;
    @Property
    private List<Rezervacija> rezervacije;
    @InjectComponent
    private Zone zoneRezervacija;
    @InjectComponent
    private Zone formZone;
    @Inject
    private Request request;
    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @PageLoaded
    void onPageLoad() {
        rezervacije = rezervacijaDao.loadAll();
    }
    
    void onActivate() {
        rezervacije = rezervacijaDao.loadAll();
    }

    @CommitAfter
    Object onSuccess() {
        rezervacijaDao.merge(rezervacija);
        rezervacije = rezervacijaDao.loadAll();
        rezervacija = new Rezervacija();
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(zoneRezervacija).addRender(formZone);
        }
        return request.isXHR() ? zoneRezervacija.getBody() : null;
    }

    @CommitAfter
    Object onActionFromDelete(int id) {
        rezervacijaDao.delete(id);
        rezervacije = rezervacijaDao.loadAll();
        return request.isXHR() ? zoneRezervacija.getBody() : null;
    }

    @CommitAfter
    Object onActionFromEdit(Rezervacija editRezervacija) {
        rezervacija = editRezervacija;
        return request.isXHR() ? formZone.getBody() : null;
    }
}
