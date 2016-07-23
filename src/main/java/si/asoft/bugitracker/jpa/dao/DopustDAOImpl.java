package si.asoft.bugitracker.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import si.asoft.bugitracker.jpa.Dopust;


@Repository
@Transactional(readOnly = true)
public class DopustDAOImpl implements DopustDAO {

	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public Dopust merge(Dopust dopust) {
		return em.merge(dopust);
	}
	
	public void insert(Dopust dopust) {
		em.persist(dopust);
	}
	
	public void flush() {
		em.flush();
	}
	
	public Dopust getDopust(Integer zaposleniId, Integer leto) {
		@SuppressWarnings("unchecked")
		List<Dopust>  temp = em.createQuery("SELECT d FROM Dopust d WHERE d.zaposleniId = :zaposleniId AND d.leto = :leto").setParameter("zaposleniId", zaposleniId).setParameter("leto", leto).getResultList();
		return temp != null && temp.size() > 0 ? temp.get(0) : null;
	}
}
