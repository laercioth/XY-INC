package br.com.xyinc.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.persistence.model.Poi;
import br.com.xyinc.persistence.dao.IPoiDAO;

@Stateless
public class PoiDAO extends GenericDAO<Poi, Long> implements IPoiDAO {

	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public List<Poi> findByName(String nome) {
		Poi poi = null;
		Criteria c = getHbCriteria();

		if (nome != null && !nome.trim().isEmpty()) {
			c.add(Restrictions.ilike("namePlace", nome, MatchMode.ANYWHERE));
		}
		List<Poi> pois = c.list();
		if (!pois.isEmpty()) {
			poi = (Poi) c.list().get(0);
		}
		return pois;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Poi> listRangebyXandY(Long x, Long y, Long range) {
		
		String hql = "FROM Poi WHERE x_coordinate <= :x and y_coordinate <= :y order by x_coordinate";
		Query query = getSession().createQuery(hql);
		query.setParameter("x", x + range);
		query.setParameter("y", y + range);
		
		return query.list();
	}
}
