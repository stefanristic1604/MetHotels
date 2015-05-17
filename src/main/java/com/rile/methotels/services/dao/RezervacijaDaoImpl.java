package com.rile.methotels.services.dao;

import com.rile.methotels.entities.Rezervacija;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Stefan
 */
public class RezervacijaDaoImpl extends GenericDaoImpl<Rezervacija> implements RezervacijaDao {

    @Override
    public List<Rezervacija> getListByIme(String ime) {
        return session.createCriteria(classType)
                .add(Restrictions.ilike("ime", ime + "%")).list();
    }

    @Override
    public List<Rezervacija> loadAllRezervacijaFromTo(int from) {
        int page = (from - 1) * 20;
        List<Rezervacija> list = session.createCriteria(classType)
                .setFirstResult(page).setMaxResults(20).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return list;
    }

    @Override
    public int getTotalRezervacija() {
        Long l = (Long) session.createCriteria(classType)
                .setProjection(Projections.rowCount()).uniqueResult();
        return l.intValue();
    }

}
