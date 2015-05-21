package com.rile.methotels.components;

import com.rile.methotels.entities.Soba;
import com.rile.methotels.services.dao.SobaDao;
import com.rile.methotels.services.dao.SobaDaoType;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Stefan
 */
public class SobaEditor extends GenericEditor<Soba, SobaDao> {
    
    @Inject 
    @SobaDaoType
    private SobaDao sobaDao;

    @Override
    protected SobaDao getGenericDao() {
        return sobaDao;
    }
    
}
