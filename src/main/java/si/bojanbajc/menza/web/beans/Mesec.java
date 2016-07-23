package si.bojanbajc.menza.web.beans;

import java.util.List;

public class Mesec {

	private String ime;
	private List<Dan> dnevi;
	
	public Mesec(String ime, List<Dan> dnevi) {
		this.ime = ime;
		this.dnevi = dnevi;
	}
	
	public String getIme() {
		return ime;
	}
	public void setImec(String ime) {
		this.ime = ime;
	}
	public List<Dan> getDnevi() {
		return dnevi;
	}
	public void setDnevi(List<Dan> dnevi) {
		this.dnevi = dnevi;
	}
}
