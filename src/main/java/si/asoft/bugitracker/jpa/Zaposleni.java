package si.asoft.bugitracker.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Zaposleni implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name = "IME")
	private String ime;
	
	@Column(name = "PRIIMEK")
	private String priimek;
	
	@Column(name = "UPO_IME")
	private String upoIme;
	
	@Column(name = "UPO_GESLO")
	private String upoGeslo;
	
	@Column(name = "ROLE")
	private String role;
	
	@Column(name = "AKTIVEN")
	private Short aktiven;
	
	@OneToMany(mappedBy="zaposleni")
	@MapKey(name="datum")
	private Map<Date, Odsotnost> odsotnostMap;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPriimek() {
		return priimek;
	}
	public void setPriimek(String priimek) {
		this.priimek = priimek;
	}
	
	
	public String getUpoIme() {
		return upoIme;
	}
	public void setUpoIme(String upoIme) {
		this.upoIme = upoIme;
	}
	
	public String getUpoGeslo() {
		return upoGeslo;
	}
	public void setUpoGeslo(String upoGeslo) {
		this.upoGeslo = upoGeslo;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public Short getAktiven() {
		return aktiven;
	}
	public void setAktiven(Short aktiven) {
		this.aktiven = aktiven;
	}
	
	public Map<Date, Odsotnost> getOdsotnostMap() {
		return odsotnostMap;
	}
	public void setOdsotnostMap(Map<Date, Odsotnost> odsotnostMap) {
		this.odsotnostMap = odsotnostMap;
	}
	
	@Transient private Long stDniDopusta;
	@Transient public Long getStDniDopusta() { return this.stDniDopusta; }
	@Transient public void setStDniDopusta(Long stDniDopusta) { this.stDniDopusta = stDniDopusta; }

	@Override
	public String toString() {
		return "Zaposleni [id=" + id + ", ime=" + ime + ", priimek=" + priimek
				+ ", upoIme=" + upoIme + ", upoGeslo=" + upoGeslo + ", role="
				+ role + ", aktiven=" + aktiven + ", odsotnostMap="
				+ odsotnostMap + ", stDniDopusta=" + stDniDopusta + "]";
	}
}
	