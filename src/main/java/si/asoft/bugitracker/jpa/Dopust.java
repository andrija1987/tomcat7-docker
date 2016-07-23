package si.asoft.bugitracker.jpa;

import java.io.Serializable;

import javax.persistence.Column;
//import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dopust")
public class Dopust implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="ID", nullable = false)
	private Integer id;
 
	@Column(name="ZAPOSLENI_ID")
	private Integer zaposleniId;
	
	@Column(name="PRENESENI_DOPUST") 
	private Integer preneseniDopust;
	
	@Column(name="REDNI_DOPUST")
	private Integer redniDopust;
	
	@Column(name="NEIZKORISCENI_DOPUST")
	private Integer neizkorisceniDopust;
	
	@Column(name="IZKORISCENI_DOPUST")
	private Integer izkorisceniDopust;
	
	@Column(name="LETO")
	private Integer leto;
	
	@Column(name="ZAPADEL_DOPUST")
	private Integer zapadelDopust;
	
	@Column(name="ZAPADLOST")
	private Integer zapadlost;
	
	@Column(name="PRENOS")
	private Integer prenos;
	
	public Dopust() {
		super();
	}
	
	public int getIdDopust() {
		return id;
	}

	public void setIdDopust(int id) {
		this.id = id;
	}

	public int getIdZaposleni() {
		return zaposleniId;
	}

	public void setIdZaposleni(int idZaposleni) {
		this.zaposleniId = idZaposleni;
	}

	public int getPreneseniDopust() {
		return preneseniDopust;
	}

	public void setPreneseniDopust(int preneseniDopust) {
		this.preneseniDopust = preneseniDopust;
	}

	public int getRedniDopust() {
		return redniDopust;
	}

	public void setRedniDopust(int redniDopust) {
		this.redniDopust = redniDopust;
	}

	public int getNeizkorisceniDopust() {
		return neizkorisceniDopust;
	}

	public void setNeizkorisceniDopust(int neizkorisceniDopust) {
		this.neizkorisceniDopust = neizkorisceniDopust;
	}

	public int getIzkorisceniDopust() {
		return izkorisceniDopust;
	}

	public void setIzkorisceniDopust(int izkorisceniDopust) {
		this.izkorisceniDopust = izkorisceniDopust;
	}
	
	public Integer getLeto() {
		return leto;
	}

	public void setLeto(Integer leto) {
		this.leto = leto;
	}

	public Integer getZapadelDopust() {
		return zapadelDopust;
	}

	public void setZapadelDopust(Integer zapadelDopust) {
		this.zapadelDopust = zapadelDopust;
	}

	public Integer getZapadlost() {
		return zapadlost;
	}

	public void setZapadlost(Integer zapadlost) {
		this.zapadlost = zapadlost;
	}

	public Integer getPrenos() {
		return prenos;
	}

	public void setPrenos(Integer prenos) {
		this.prenos = prenos;
	}

	@Override
	public String toString() {
		return "Dopust [zaposleniId=" + zaposleniId
				+ ", preneseniDopust=" + preneseniDopust + ", redniDopust="
				+ redniDopust + ", neizkorisceniDopust=" + neizkorisceniDopust
				+ ", izkorisceniDopust=" + izkorisceniDopust + "]";
	}

	@Override 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((zaposleniId == null) ? 0 : zaposleniId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dopust other = (Dopust) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (zaposleniId == null) {
			if (other.zaposleniId != null)
				return false;
		} else if (!zaposleniId.equals(other.zaposleniId))
			return false;
		return true;
	}
}
