package si.asoft.bugitracker.jpa.dao;

import java.util.Date;

import si.asoft.bugitracker.jpa.Odsotnost;

public interface OdsotnostDAO {

	public Odsotnost merge(Odsotnost odsotnost);
	public void insert(Odsotnost odsotnost);
	public void delete(Date datum, Integer zaposleniId);
	public void flush();
	public Odsotnost getOdsotnost(Integer zaposleniId, Date datum);
	public Long getSumDopust(Integer zaposleniId, Date datumOd, Date datumDo);
}
