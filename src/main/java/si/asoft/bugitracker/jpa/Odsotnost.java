package si.asoft.bugitracker.jpa;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Odsotnost implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OdsotnostId id;
	
	@Column(name="ODSOTNOST_RAZLOG_ID", updatable=false, insertable=false)
	private Integer razlogId;
	
	@ManyToOne
	@JoinColumn(name="ODSOTNOST_RAZLOG_ID", updatable=false, insertable=false)
	private OdsotnostRazlog razlog;
	
	@Column(name="ZAPOSLENI_ID", updatable=false, insertable=false)
	private Integer zaposleniId;
	
	@ManyToOne
	@JoinColumn(name="ZAPOSLENI_ID", updatable=false, insertable=false)
	private Zaposleni zaposleni;
	
	@Column(name="DATUM", updatable=false, insertable=false)
	private Date datum;
	
	@Column(name="KOMENTAR")
	private String komentar;
	
	public Odsotnost() {
		super();
	}
	
	public Odsotnost(OdsotnostId id) {
		this.id = id;
	}

	public OdsotnostId getId() {
		return id;
	}

	public void setId(OdsotnostId id) {
		this.id = id;
	}

	public Integer getRazlogId() {
		return razlogId;
	}

	public void setRazlogId(Integer razlogId) {
		this.razlogId = razlogId;
	}
	
	public OdsotnostRazlog getRazlog() {
		return razlog;
	}

	public void setRazlog(OdsotnostRazlog razlog) {
		this.razlog = razlog;
	}

	public Integer getZaposleniId() {
		return zaposleniId;
	}

	public void setZaposleniId(Integer zaposleniId) {
		this.zaposleniId = zaposleniId;
	}
	public Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}
	
	@Transient
	public String getDatumToString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		if (this.datum != null) {
			return sdf.format(getDatum());
		}
		return "";
	}
	
}