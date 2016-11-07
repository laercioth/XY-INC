package br.com.xyinc.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.persistence.model.Poi;

@Local
public interface IPoiDAO extends IGenericDAO<Poi, Long> {

	/**
	 * Find localization byName
	 * 
	 * @param nome
	 * @return
	 */
	List<Poi> findByName(String nome);
	
	/**
	 * Find localization nearby to the x and y informed and one point
	 * @param nome
	 * @return
	 */
	List<Poi> listRangebyXandY(Long x, Long y, Long range);

}
