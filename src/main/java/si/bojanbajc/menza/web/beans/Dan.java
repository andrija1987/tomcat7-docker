package si.bojanbajc.menza.web.beans;

import java.util.Date;

import si.asoft.bugitracker.jpa.Praznik;

public class Dan {

	private Integer st;
	private String ime;
	private Date datum;
	private Praznik praznik;
	private boolean vikend;
	
	public Integer getSt() {
		return st;
	}
	public void setSt(Integer st) {
		this.st = st;
	}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public Praznik getPraznik() {
		return praznik;
	}
	public void setPraznik(Praznik praznik) {
		this.praznik = praznik;
	}
	public boolean isVikend() {
		return vikend;
	}
	public void setVikend(boolean vikend) {
		this.vikend = vikend;
	}
}
