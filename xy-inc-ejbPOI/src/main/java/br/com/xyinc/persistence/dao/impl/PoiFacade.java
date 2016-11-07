package br.com.xyinc.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import br.com.persistence.model.Poi;
import br.com.xyinc.business.facade.IPoiFacade;
import br.com.xyinc.persistence.dao.IPoiDAO;

@Stateless
public class PoiFacade implements IPoiFacade {

	private static final Logger LOGGER = Logger.getLogger(PoiFacade.class);

	@Inject
	private IPoiDAO userDAO;

	@Override
	@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
	public List<Poi> listAll() {
		List<Poi> users = new ArrayList<>();
		try {
			users = userDAO.listAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return users;
	}

	@Override
	public Poi save(Poi user) throws Exception {
		Poi u = new Poi();
		try {
			u = userDAO.saveOrUpdate(user);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return u;
	}

	@Override
	public List<Poi> listRangeByXandY(Long x, Long y, Long range) throws Exception {
		
		List<Poi> users = new ArrayList<>();
		try {
			users = userDAO.listRangebyXandY(x, y, range);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return users;
	}
}
