package si.asoft.bugitracker.jpa.dao;

import si.asoft.bugitracker.jpa.Dopust;

public interface DopustDAO {

	public Dopust merge(Dopust dopust);
	public void insert(Dopust dopust);
	public void flush();
	public Dopust getDopust(Integer zaposleniId, Integer leto);
}
