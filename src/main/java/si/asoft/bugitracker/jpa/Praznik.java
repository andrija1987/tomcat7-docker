package si.asoft.bugitracker.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Praznik implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="DATUM")
	private Date datum;
	
	@Column(name="DAN")
	private String dan;
	
	@Column(name="LETO")
	private Short leto;
	
	@Column(name="NAZIV")
	private String naziv;
	
	@Column(name="PROSTI_DAN")
	private Short prostiDan;
	
	@Column(name="VERSKI_PRAZNIK")
	private Short verskiPraznik;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getDan() {
		return dan;
	}

	public void setDan(String dan) {
		this.dan = dan;
	}

	public Short getLeto() {
		return leto;
	}

	public void setLeto(Short leto) {
		this.leto = leto;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Short getProstiDan() {
		return prostiDan;
	}

	public void setProstiDan(Short prostiDan) {
		this.prostiDan = prostiDan;
	}

	public Short getVerskiPraznik() {
		return verskiPraznik;
	}

	public void setVerskiPraznik(Short verskiPraznik) {
		this.verskiPraznik = verskiPraznik;
	}
}
	