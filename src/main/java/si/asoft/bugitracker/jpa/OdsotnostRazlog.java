package si.asoft.bugitracker.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "ODSOTNOST_RAZLOG")
public class OdsotnostRazlog {

	private Integer id;
	private String naziv;
	private String barva;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public String getBarva() {
		return barva;
	}
	public void setBarva(String barva) {
		this.barva = barva;
	}
}
