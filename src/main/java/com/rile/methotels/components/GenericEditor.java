package com.rile.methotels.components;

import com.rile.methotels.entities.AbstractEntity;
import com.rile.methotels.services.dao.GenericDao;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PropertyConduit;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.PropertyConduitSource;

/**
 *
 * @author Stefan
 */
public class GenericEditor<T extends AbstractEntity> {

    @Inject
    private PropertyConduitSource conduit;
    @Inject
    private GenericDao genericDao;
    @Persist
    @Property
    private T bean;
    @Property
    private T row;
    @Inject
    private BeanModelSource beanModelSource;
    @Inject
    private ComponentResources componentResources;
    @Parameter
    private Class klasa;
    
    {
        PropertyConduit conduit1 = conduit.create(getClass(), "bean");
        klasa = conduit1.getPropertyType();
    }
    
    public List<T> getGrid() {
        return genericDao.loadAll();
    }

    public BeanModel<T> getFormModel() {
        return beanModelSource.createEditModel(
                klasa, componentResources.getMessages()).exclude("id");
    }

    public BeanModel<T> getGridModel() {
        return beanModelSource.createDisplayModel(
                klasa, componentResources.getMessages()).exclude("id");
    }

    @CommitAfter
    Object onActionFromDelete(int id) {
        genericDao.delete(id);
        return this;
    }

    @CommitAfter
    Object onActionFromEdit(int row) {
        bean = (T) genericDao.getByID(row);
        return this;
    }

    @CommitAfter
    public Object onSuccess() {
        genericDao.merge(bean);
        try {
            bean = (T) klasa.newInstance();
        } catch (Exception ex) {
        }
        return this;
    }
}
