package com.rile.methotels.components;

import com.rile.methotels.entities.AbstractEntity;
import com.rile.methotels.services.dao.GenericDao;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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
    protected Class clazz;
    @Property
    protected List<T> grid;
    
    {
        PropertyConduit conduit1 = conduit.create(getClass(), "bean");
        clazz = conduit1.getPropertyType();
    }

    @SetupRender
    void setupRender() {
        grid = getGenericDao().loadAll();
    }

    private String[] getExcludedFieldsNames(Class clazz) {
        List<String> excludedFieldsNames = new ArrayList<String>();
        
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().toLowerCase().contains("id")) {
                excludedFieldsNames.add(field.getName());
            }
        }
        Class superClazz = clazz.getSuperclass();
        if (superClazz != null) {
            excludedFieldsNames.addAll(Arrays.asList(getExcludedFieldsNames(superClazz)));
        }
        return excludedFieldsNames.toArray(new String[excludedFieldsNames.size()]);
    }
    
    public BeanModel<T> getFormModel() {
        return beanModelSource.createEditModel(clazz, componentResources.getMessages())
                .exclude(getExcludedFieldsNames(clazz));
    }

    public BeanModel<T> getGridModel() {
        return beanModelSource.createDisplayModel(clazz, componentResources.getMessages())
                .exclude(getExcludedFieldsNames(clazz));
    }

    @CommitAfter
    Object onActionFromDelete(int id) {
        getGenericDao().delete(id);
        return null;
    }

    @CommitAfter
    Object onActionFromEdit(int row) {
        bean = (T) getGenericDao().getByID(row);
        return null;
    }

    @CommitAfter
    public Object onSuccess() {
        if (bean != null) {
            boolean elementExists = grid.indexOf(bean) != -1;
            if (elementExists) {
                grid.set(grid.indexOf(bean), getGenericDao().merge(bean));
            } else {
                grid.add(getGenericDao().merge(bean));
            }
            try {
                bean = (T) clazz.newInstance();
            } catch (Exception ex) {}
        }
        return null;
    }
    
    protected abstract DAO getGenericDao();
    
}
