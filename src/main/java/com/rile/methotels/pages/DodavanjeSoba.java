package com.rile.methotels.pages;

import com.rile.methotels.components.GenericEditor;
import com.rile.methotels.entities.Soba;
import com.rile.methotels.services.dao.SobaDao;
import com.rile.methotels.services.security.ProtectedPage;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.annotations.Component;
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

    
//    @Component
//    @Property
//    private GenericEditor<Soba, SobaDao> ge;
    
    void onActivate() {
        sobe = sobaDao.loadAll();
    }

    @CommitAfter
    Object onSuccess() {
        sobaDao.merge(soba);
        return null;
    }

    @CommitAfter
    Object onActionFromDelete(int id) {
        sobaDao.delete(id);
        return null;
    }

    @CommitAfter
    Object onActionFromEdit(Soba s) {
        soba = s;
        return null;
    }
}
