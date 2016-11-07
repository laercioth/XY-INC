package br.com.xyinc.business.facade;

import java.util.List;

import javax.ejb.Local;

import br.com.persistence.model.Poi;

@Local
public interface IPoiFacade {

	/**
	 * List all positions
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Poi> listAll() throws Exception;

	/**
	 * Save new position
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Poi save(Poi user) throws Exception;

	/**
	 * List all places around of the range
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws Exception
	 */
	List<Poi> listRangeByXandY(Long x, Long y, Long range) throws Exception;

}
