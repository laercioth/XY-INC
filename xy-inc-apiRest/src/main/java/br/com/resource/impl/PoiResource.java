package br.com.resource.impl;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.persistence.model.Poi;
import br.com.resource.IPoi;
import br.com.xyinc.business.facade.IPoiFacade;


@Path("/pois")
public class PoiResource implements IPoi {

	@Inject
	private IPoiFacade poiFacade;
	public static final String DATA_INCORRETTLY = "Favor preencher os dados corretamente!";
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response listAll() throws Exception {
		List<Poi> pois = poiFacade.listAll();
		return Response.status(200).entity(pois).build();
	}

	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Response save(Poi poi) throws Exception {
		
		if(poi == null)
			throw new IllegalArgumentException(DATA_INCORRETTLY);
		
		poiFacade.save(poi);
		return Response.status(201).entity(poi).build();
	}

	@GET
	@Path("/{x}/{y}/{range}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response listRangeByXandY(
			@PathParam("x")Long x, 
			@PathParam("y")Long y,
			@PathParam("range")Long range
			) throws Exception {
		
		if(x == null || y == null || range == null)
			throw new IllegalArgumentException(DATA_INCORRETTLY);
		
		List<Poi> pois = poiFacade.listRangeByXandY(x, y, range);
		return Response.status(200).entity(pois).build();
	}

}
