package si.asoft.bugitracker.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import si.asoft.bugitracker.jpa.OdsotnostRazlog;

@Repository
@Transactional(readOnly = true)
public class OdsotnostRazlogDAOImpl implements OdsotnostRazlogDAO {

	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<OdsotnostRazlog> getOdsotnostRazlogList() {
		Criteria criteria = ((Session)em.getDelegate()).createCriteria(OdsotnostRazlog.class);
		return criteria.list();
	}
	
}
