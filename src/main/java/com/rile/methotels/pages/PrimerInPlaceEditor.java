package com.rile.methotels.pages;

import com.rile.methotels.entities.Soba;
import com.rile.methotels.services.dao.SobaDao;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.got5.tapestry5.jquery.components.InPlaceEditor;

/**
 *
 * @author Stefan
 */
public class PrimerInPlaceEditor {

    @Property
    private Soba soba;
    @Property
    private List<Soba> sobe;
    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;
    @Inject
    private SobaDao sobaDao;
    @Inject
    private ComponentResources componentResources;

    void onActivate() {
        sobe = sobaDao.loadAll();
    }

    @CommitAfter
    @OnEvent(component = "nazivSobe", value = InPlaceEditor.SAVE_EVENT)
    void setNazivSobe(Long id, String value) {
        soba = sobaDao.getByID(id.intValue());
        soba.setNaziv(value);
        sobaDao.merge(soba);
    }
}
