package br.com.resource;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import br.com.persistence.model.Poi;

public interface IPoi {

	/**
	 * Find all POI'S
	 * 
	 * @return
	 * @throws Exception
	 */
	public Response listAll() throws Exception;

	/**
	 * Save POI specificated
	 * 
	 * @param poi
	 * @return
	 * @throws Exception
	 */
	public Response save(Poi poi) throws Exception;

	/**
	 * Find POI'S on range of the position indicated
	 * 
	 * @param x
	 * @param y
	 * @param range
	 * @return
	 * @throws Exception
	 */
	public Response listRangeByXandY(
			@PathParam("x") Long x, 
			@PathParam("y") Long y, 
			@PathParam("range") Long range)
			throws Exception;
}
