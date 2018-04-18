package edu.vt.crest.hr.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import edu.vt.crest.hr.entity.DepartmentEntity;
import edu.vt.crest.hr.services.DepartmentService;

/**
 * Serves as a RESTful endpoint for manipulating DepartmentEntity(s)
 */
@Stateless
@Path("localhost/departments")
public class DepartmentResource {

	// Used to interact with DepartmentEntity(s)
	@Inject
	DepartmentService departmentService;

	/**
	 * TODO - Implement this method
	 * 
	 * @param department
	 *            the DepartmentEntity to create
	 * @return a Response containing the new DepartmentEntity
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(DepartmentEntity department) {
		DepartmentEntity currentDepartment = departmentService.createDepartment(department);
		return Response.ok(currentDepartment).build();
	}

	/**
	 * TODO - Implement this method
	 * 
	 * @param id
	 *            of the DepartmentEntity to return
	 * @return a Response containing the matching DepartmentEntity
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Long id) {
		DepartmentEntity currentDepartment = departmentService.findById(id);
		if (currentDepartment != null) {
			return Response.ok(currentDepartment).build();
		} else {
			return Response.noContent().build();
		}
	}

	/**
	 * TODO - Implement this method
	 * 
	 * @param startPosition
	 *            the index of the first DepartmentEntity to return
	 * @param maxResult
	 *            the maximum number of DepartmentEntity(s) to return beyond the
	 *            startPosition
	 * @return a list of DepartmentEntity(s)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DepartmentEntity> listAll(@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {

		return departmentService.listAll(startPosition, maxResult);
	}

	/**
	 * TODO - Implement this method
	 * 
	 * @param id
	 *            the id of the DepartmentEntity to update
	 * @param department
	 *            the entity used to update
	 * @return a Response containing the updated DepartmentEntity
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, DepartmentEntity department) {
		try {
			DepartmentEntity currentDepartment = departmentService.update(id, department);
			if (currentDepartment != null) {
				return Response.ok(currentDepartment).build();
			} else {
				return Response.notModified().build();
			}
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
		}
	}

}