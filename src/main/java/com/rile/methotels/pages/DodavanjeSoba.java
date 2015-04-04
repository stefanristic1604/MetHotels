package com.rile.methotels.pages;

import com.rile.methotels.entities.Soba;
import com.rile.methotels.services.SobaDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Stefan
 */
public class DodavanjeSoba {

    @Property
    private Soba soba;

    @Property
    private Soba oneSoba;

    @Property
    private List<Soba> sobe;

    @Inject
    private SobaDao sobaDao;

    void onActivate() {
        if (sobe == null) {
            sobe = new ArrayList<Soba>();
        }
        sobe = sobaDao.getListSoba();
    }

    @CommitAfter
    Object onSuccess() {
        sobaDao.addSoba(soba);
        return this;
    }
    
    @CommitAfter
    Object onActionFromDelete(int id) {
        sobaDao.removeSoba(id);
        return this;
    }

}
