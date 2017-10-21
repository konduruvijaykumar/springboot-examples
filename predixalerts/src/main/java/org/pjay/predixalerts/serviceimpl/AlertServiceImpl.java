/**
 * 
 */
package org.pjay.predixalerts.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.pjay.predixalerts.entity.Alert;
import org.pjay.predixalerts.repository.AlertRepository;
import org.pjay.predixalerts.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vkonduru3
 *
 */
// IMP::
// http://spring.io/blog/2011/02/10/getting-started-with-spring-data-jpa
// https://stackoverflow.com/questions/19930152/what-is-persistence-context
@Service
public class AlertServiceImpl implements AlertService {
	
	//@Autowired
	private final AlertRepository alertRepository;
	
	@Autowired
	//@PersistenceContext
	// https://stackoverflow.com/questions/19930152/what-is-persistence-context
	private EntityManager entityManager;

	public AlertServiceImpl(AlertRepository alertRepository) {
		this.alertRepository = alertRepository;
	}

	@Override
	@Transactional
	public void saveAlertData(Alert alert) {
		alertRepository.save(alert);
	}

	// IMP
	// https://docs.jboss.org/hibernate/entitymanager/3.5/reference/en/html/metamodel.html
	// https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
	// http://docs.jboss.org/hibernate/jpamodelgen/1.0/reference/en-US/html_single/#whatisit
	//criteriaBuilder.equals(root.get(Alert_.id), alert.getId());
	@Override
	public List<Alert> getAlertData(Alert alert) {
		// VIMP::
		// With entity manager you can create all queries like named, native, procedures, criterion etc. Just like working with hibernate session factory, with all possibilities.
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Alert> criteriaQuery = criteriaBuilder.createQuery(Alert.class);
		Root<Alert> root = criteriaQuery.from(Alert.class);
		List<Predicate> predicates = new ArrayList<>();
		if(null != alert.getId()) {
			// IMP
			// https://docs.jboss.org/hibernate/entitymanager/3.5/reference/en/html/metamodel.html
			// https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
			// http://docs.jboss.org/hibernate/jpamodelgen/1.0/reference/en-US/html_single/#whatisit
			//criteriaBuilder.equals(root.get(Alert_.id), alert.getId());
			predicates.add(criteriaBuilder.equal(root.get("id"), alert.getId()));
		}
		if(null != alert.getLocation())
			predicates.add(criteriaBuilder.equal(root.get("location"), alert.getLocation()));
		if(null != alert.getMachine())
			predicates.add(criteriaBuilder.equal(root.get("machine"), alert.getMachine()));
		if(null != alert.getParameter())
			predicates.add(criteriaBuilder.equal(root.get("parameter"), alert.getParameter()));
		if(null != alert.getDescription())
			predicates.add(criteriaBuilder.equal(root.get("description"), alert.getDescription()));
		if(null != alert.getAction())
			predicates.add(criteriaBuilder.equal(root.get("action"), alert.getAction()));
		if(null != alert.getAlertDate())
			predicates.add(criteriaBuilder.equal(root.get("alertDate"), alert.getAlertDate()));
		// This is not useful it can impact in other ways, hence commenting till fixing issue
		//if(null != alert.getCreationDate())
			//predicates.add(criteriaBuilder.equal(root.get("creationDate"), alert.getCreationDate()));

		// Note: I think we should avoid creating where condition when size of predicates is empty, else it will result in where 1=1 in hibernate query. Which will still get complete data which is still fine
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		//entityManager.createQuery(criteriaQuery.select(root)).getResultList();
		
		return entityManager.createQuery(criteriaQuery.select(root)).getResultList();
	}

}
