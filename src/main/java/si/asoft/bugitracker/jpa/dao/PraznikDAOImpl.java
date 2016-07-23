package si.asoft.bugitracker.jpa.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import si.asoft.bugitracker.jpa.Praznik;

@Repository
@Transactional(readOnly = true)
public class PraznikDAOImpl implements PraznikDAO {

	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Praznik> getPraznikList(Short leto) {
		return em.createQuery("SELECT p FROM Praznik p WHERE p.leto = :leto ORDER BY datum ASC").setParameter("leto", leto).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Praznik> getPraznikList(Date datumOd, Date datumDo) {
		return em.createQuery("SELECT p FROM Praznik p WHERE p.datum between :datumOd and :datumDo ORDER BY datum ASC")
				.setParameter("datumOd", datumOd).setParameter("datumDo", datumDo).getResultList();
	}
}
