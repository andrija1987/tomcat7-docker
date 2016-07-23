package si.asoft.bugitracker.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import si.asoft.bugitracker.jpa.Zaposleni;

@Repository
@Transactional(readOnly = true)
public class ZaposleniDAOImpl implements ZaposleniDAO {

	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public Zaposleni getZaposleni(Integer id) {
		return em.find(Zaposleni.class, id);
	}
	
	public Zaposleni getZaposleni(String upoIme) {
		@SuppressWarnings("unchecked")
		List<Zaposleni>  temp = em.createQuery("SELECT z FROM Zaposleni z WHERE z.upoIme = :upoIme").setParameter("upoIme", upoIme).getResultList();
		return temp != null && temp.size() > 0 ? temp.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Zaposleni> getZaposleniList() {
		return em.createQuery("SELECT z FROM Zaposleni z WHERE z.aktiven = 1 ORDER BY priimek, ime").getResultList();
	}
	
}
