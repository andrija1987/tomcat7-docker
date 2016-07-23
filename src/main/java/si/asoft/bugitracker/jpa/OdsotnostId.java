package si.asoft.bugitracker.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OdsotnostId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="ODSOTNOST_RAZLOG_ID")
	private int odsotnostRazlogId;

	@Column(name="ZAPOSLENI_ID")
	private int zaposleniId;
	
	@Column(name="DATUM")
	private Date datum;
	
	public OdsotnostId() {
		super();
	}
	
	public OdsotnostId(Integer odsotnostRazlogId, Integer zaposleniId, Date datum) {
		this.odsotnostRazlogId = odsotnostRazlogId;
		this.zaposleniId = zaposleniId;
		this.datum = datum;
	}

	public int getOdsotnostRazlogId() {
		return odsotnostRazlogId;
	}

	public void setOdsotnostRazlogId(int odsotnostRazlogId) {
		this.odsotnostRazlogId = odsotnostRazlogId;
	}

	public int getZaposleniId() {
		return zaposleniId;
	}

	public void setZaposleniId(int zaposleniId) {
		this.zaposleniId = zaposleniId;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result + odsotnostRazlogId;
		result = prime * result + zaposleniId;
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
		OdsotnostId other = (OdsotnostId) obj;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (odsotnostRazlogId != other.odsotnostRazlogId)
			return false;
		if (zaposleniId != other.zaposleniId)
			return false;
		return true;
	}
	
	
	
}
