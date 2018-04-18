package edu.vt.crest.hr.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import edu.vt.crest.hr.entity.DepartmentEntity;

/**
 * Implements a DepartmentService
 */
@ApplicationScoped
public class DepartmentServiceBean implements DepartmentService {

	@PersistenceContext(unitName = "crest-hr-persistence-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DepartmentEntity createDepartment(DepartmentEntity department) {
		em.persist(department);
		return department;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DepartmentEntity findById(Long id) {
		return em.find(DepartmentEntity.class, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DepartmentEntity> listAll(Integer startPosition, Integer maxResult) {

		Query theQuery = em.createQuery("select d from DepartmentEntity d", DepartmentEntity.class);
		theQuery.setFirstResult(startPosition);
		theQuery.setMaxResults(maxResult);
		return theQuery.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DepartmentEntity update(Long id, DepartmentEntity department) throws OptimisticLockException {
		DepartmentEntity departmentDB = em.find(DepartmentEntity.class, id);
		if (departmentDB != null) {
			em.getTransaction().begin();
			departmentDB.setIdentifier(department.getIdentifier());
			departmentDB.setName(department.getName());
			departmentDB.setVersion(department.getVersion());
			em.getTransaction().commit();

		}
		return departmentDB;
	}

}
