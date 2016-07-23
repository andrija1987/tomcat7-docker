package si.asoft.bugitracker.jpa.dao;

import java.util.List;

import si.asoft.bugitracker.jpa.Zaposleni;

public interface ZaposleniDAO {

	public Zaposleni getZaposleni(Integer id);
	public Zaposleni getZaposleni(String ime);
	public List<Zaposleni> getZaposleniList();
}
