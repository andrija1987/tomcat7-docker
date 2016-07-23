package si.asoft.bugitracker.jpa.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import si.asoft.bugitracker.jpa.Odsotnost;

@Repository
@Transactional(readOnly = true)
public class OdsotnostDAOImpl implements OdsotnostDAO {

	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public Odsotnost merge(Odsotnost odsotnost) {
		return em.merge(odsotnost);
	}
	
	public void insert(Odsotnost odsotnost) {
		em.persist(odsotnost);
	}
	
	public void delete(Date datum, Integer zaposleniId) {
		/*Odsotnost odsotnost = getOdsotnost(zaposleniId, datum);
		if (odsotnost != null) {
			em.remove(odsotnost);
		}*/
		em.createQuery("DELETE FROM Odsotnost o WHERE o.datum = :datum AND o.zaposleniId = :zaposleniId")
			.setParameter("datum", datum)
			.setParameter("zaposleniId", zaposleniId)
			.executeUpdate();
	}
	
	public void flush() {
		em.flush();
	}
	
	public Odsotnost getOdsotnost(Integer zaposleniId, Date datum) {
		@SuppressWarnings("unchecked")
		List<Odsotnost>  temp = em.createQuery("SELECT o FROM Odsotnost o WHERE o.zaposleniId = :zaposleniId AND o.datum = :datum").setParameter("zaposleniId", zaposleniId).setParameter("datum", datum).getResultList();
		return temp != null && temp.size() > 0 ? temp.get(0) : null;
	}
	
	public Long getSumDopust(Integer zaposleniId, Date datumOd, Date datumDo) {
		String sql = "SELECT COUNT(*) FROM Odsotnost o WHERE o.razlogId = 2 AND o.zaposleniId = :zaposleniId AND o.datum >= :datumOd AND o.datum <= :datumDo";
		Query q = em.createQuery(sql);
		q.setParameter("zaposleniId", zaposleniId);
		q.setParameter("datumOd", datumOd);
		q.setParameter("datumDo", datumDo);
		return (Long)q.getSingleResult();
	}
	
}
