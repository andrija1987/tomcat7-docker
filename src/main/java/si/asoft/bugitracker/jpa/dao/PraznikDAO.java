package si.asoft.bugitracker.jpa.dao;

import java.util.Date;
import java.util.List;

import si.asoft.bugitracker.jpa.Praznik;

public interface PraznikDAO {

	public List<Praznik> getPraznikList(Short leto);
	public List<Praznik> getPraznikList(Date datumOd, Date datumDo);
}
