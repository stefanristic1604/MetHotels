package com.rile.methotels.components;

import com.rile.methotels.entities.AbstractEntity;
import com.rile.methotels.services.dao.GenericDao;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PropertyConduit;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.PropertyConduitSource;

/**
 *
 * @author Stefan
 */
public abstract class GenericEditor<T extends AbstractEntity, DAO extends GenericDao<T>> {

    @Inject
    protected PropertyConduitSource conduit;
    //@Inject
    //protected DAO getGenericDao;
    @Persist
    @Property
    protected T bean;
    @Property
    protected T row;
    @Inject
    protected BeanModelSource beanModelSource;
    @Inject
    protected ComponentResources componentResources;
    @Parameter
    protected Class klasa;
    @Property
    protected List<T> grid;
    
    {
        PropertyConduit conduit1 = conduit.create(getClass(), "bean");
        klasa = conduit1.getPropertyType();
    }
    /*
    @PageLoaded
    void onPageLoad() {
    }*/

    @SetupRender
    void setupRender() {
        grid = getGenericDao().loadAll();
    }
    
    /*
    public List<T> getGrid() {
        return getGenericDao().loadAll();
    }
    */
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
        getGenericDao().delete(id);
        return this;
    }

    @CommitAfter
    Object onActionFromEdit(int row) {
        bean = (T) getGenericDao().getByID(row);
        return this;
    }

    @CommitAfter
    public Object onSuccess() {
        getGenericDao().merge(bean);
        try {
            bean = (T) klasa.newInstance();
        } catch (Exception ex) {
        }
        return this;
    }
    
    protected abstract DAO getGenericDao();
    
}
