package com.rile.methotels.pages;

import com.rile.methotels.entities.Soba;
import com.rile.methotels.services.dao.SobaDao;
import com.rile.methotels.services.security.ProtectedPage;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Stefan
 */
@ProtectedPage
@RolesAllowed(value = {"Admin", "Sluzbenik"})
public class DodavanjeSoba {

    @Persist
    @Property
    private Soba soba;

    @Property
    private Soba oneSoba;

    @Property
    private List<Soba> sobe;

    @Inject
    private SobaDao sobaDao;

    void onActivate() {}

    @PageLoaded
    void onPageLoad() {
        sobe = sobaDao.loadAll();
    }

    @CommitAfter
    Object onSuccess() {
        if (soba != null) {
            boolean elementExists = sobe.indexOf(soba) != -1;
            if (elementExists) {
                sobe.set(sobe.indexOf(soba), sobaDao.merge(soba));
            } else {
                sobe.add(sobaDao.merge(soba));
            }
            soba = new Soba();
        }
        return null;
    }
    
    @CommitAfter
    Object onActionFromDelete(int id) {
        sobe.remove(sobaDao.delete(id));
        return null;
    }

    @CommitAfter
    Object onActionFromEdit(Soba editSoba) {
        if (editSoba != null) {
            soba = editSoba;
        }
        return null;
    }

}
