package edu.vt.crest.hr.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import edu.vt.crest.hr.entity.EmployeeEntity;

/**
 * Implements an EmployeeService
 */
@ApplicationScoped
public class EmployeeServiceBean implements EmployeeService {

	@PersistenceContext(unitName = "crest-hr-persistence-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeEntity createEmployee(EmployeeEntity employee) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeEntity findById(Long id) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EmployeeEntity> listAll(Integer startPosition, Integer maxResult) {
		Query theQuery = em.createQuery("Select e from EmployeeEntity e", EmployeeEntity.class);
		theQuery.setFirstResult(startPosition);
		theQuery.setMaxResults(maxResult);
		return theQuery.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeEntity update(Long id, EmployeeEntity employee) throws OptimisticLockException {
		EmployeeEntity employeeDB = em.find(EmployeeEntity.class, id);
		if (employeeDB != null) {
			em.getTransaction().begin();
			employeeDB.setFirstName(employee.getFirstName());
			employeeDB.setLastName(employee.getLastName());
			employeeDB.setVersion(employee.getVersion());
			em.getTransaction().commit();
		}
		return employeeDB;
	}
}
