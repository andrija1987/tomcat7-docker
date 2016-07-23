package si.asoft.bugitracker.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import si.asoft.bugitracker.jpa.Dopust;
//import si.asoft.bugitracker.jpa.DopustId;
import si.asoft.bugitracker.jpa.Odsotnost;
import si.asoft.bugitracker.jpa.OdsotnostId;
import si.asoft.bugitracker.jpa.OdsotnostRazlog;
import si.asoft.bugitracker.jpa.Praznik;
import si.asoft.bugitracker.jpa.Zaposleni;
import si.asoft.bugitracker.jpa.dao.DopustDAO;
import si.asoft.bugitracker.jpa.dao.OdsotnostDAO;
import si.asoft.bugitracker.jpa.dao.OdsotnostRazlogDAO;
import si.asoft.bugitracker.jpa.dao.PraznikDAO;
import si.asoft.bugitracker.jpa.dao.ZaposleniDAO;
import si.bojanbajc.menza.web.beans.Dan;

@Controller
public class AdminController {
	
	private static Logger log = Logger.getLogger(AdminController.class);
	
	@Autowired ZaposleniDAO zaposleniDAO;
	@Autowired OdsotnostRazlogDAO odsotnostRazlogDAO;
	@Autowired OdsotnostDAO odsotnostDAO;
	@Autowired PraznikDAO praznikDAO;
	@Autowired DopustDAO dopustDAO;
	
	private Calendar calendar;
	private Locale locale;
	
	private Odsotnost odsotnost;
	private Dopust dopust;
	
	Calendar datumPrenosa = new GregorianCalendar();
	Calendar datumZapadlosti = new GregorianCalendar();
	Calendar trenutniDatum = new GregorianCalendar();
	
	@RequestMapping(value="/admin/bugi-tracker", method=RequestMethod.GET) 
	public ModelAndView startForm() {
		log.debug("startForm()");
		
		if (locale == null) {
			locale = new Locale("sl");
		}
		calendar = new GregorianCalendar(locale);
		return startForm(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
	}

	@RequestMapping(value="/admin/bugi-tracker/{leto}/{mesec}", method=RequestMethod.GET)
	public ModelAndView startForm(@PathVariable Integer leto, @PathVariable Integer mesec) {
		log.debug("startForm()");
		if (calendar == null) {
			locale = new Locale("sl");
			calendar = new GregorianCalendar(locale);
		}
		if (leto < 2000) {
			leto = 2000;
		}
		if (mesec < 0 || mesec > 11) {
			mesec = 0;
		}
		calendar.set(leto, mesec, 1, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return getKoledar(leto);
	}
	
	private ModelAndView getKoledar(Integer leto) {
		
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM yyyy", locale);
		SimpleDateFormat dayFormat = new SimpleDateFormat("EEE", locale); 

		Calendar letoStart = (Calendar)calendar.clone();
		letoStart.set(letoStart.get(Calendar.YEAR), 0, 1, 0, 0, 0);
		
		Calendar letoEnd = (Calendar)calendar.clone();
		letoEnd.set(letoEnd.get(Calendar.YEAR), 11, 31, 0, 0, 0);

		Calendar temp = (Calendar)calendar.clone();
		Calendar temp2 = (Calendar)calendar.clone();
		
		int min = temp.getActualMinimum(Calendar.DAY_OF_MONTH);
		int max = temp.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		Calendar mesecStart = (Calendar)calendar.clone();
		mesecStart.set(mesecStart.get(Calendar.YEAR), mesecStart.get(Calendar.MONTH), 1, 0, 0, 0);
		
		Calendar mesecEnd = (Calendar)calendar.clone();
		mesecEnd.set(mesecEnd.get(Calendar.YEAR), mesecEnd.get(Calendar.MONTH), max, 0, 0, 0);
		
		List<Praznik> praznikList = praznikDAO.getPraznikList(mesecStart.getTime(), mesecEnd.getTime());
		
		List<Zaposleni> zaposleniList = zaposleniDAO.getZaposleniList();
		List<OdsotnostRazlog> odsotnostRazlogList = odsotnostRazlogDAO.getOdsotnostRazlogList();
		
		datumPrenosa.set((Calendar.getInstance().get(Calendar.YEAR)), 0, 1, 0, 0, 0);
		datumZapadlosti.set((Calendar.getInstance().get(Calendar.YEAR)), 6, 1, 0, 0, 0);
		
		//trenutniDatum.set((Calendar.getInstance().get(Calendar.YEAR)), 6, 2, 0, 0, 0);
		
		for (Zaposleni zaposleni : zaposleniList) {
			
			zaposleni.setStDniDopusta(odsotnostDAO.getSumDopust(zaposleni.getId(), letoStart.getTime(), letoEnd.getTime()));
			
			dopust = dopustDAO.getDopust(zaposleni.getId(), Calendar.getInstance().get(Calendar.YEAR));
			
			if(dopust == null) {
				setDefaultDopust(zaposleni.getId());
			}
			
			Integer prenos = dopust.getPrenos() != null ? dopust.getPrenos() : 0;
			
			if (prenos == 0 && datumPrenosa.before(trenutniDatum)) {
				
				Dopust tempDopust = dopustDAO.getDopust(zaposleni.getId(), (Calendar.getInstance().get(Calendar.YEAR)) - 1);
				
				if(tempDopust != null) {
					
					dopust = dopustDAO.merge(dopust);
					
					dopust.setPreneseniDopust(tempDopust.getNeizkorisceniDopust());
					dopust.setLeto(Calendar.getInstance().get(Calendar.YEAR));
					dopust.setNeizkorisceniDopust(tempDopust.getNeizkorisceniDopust());
					dopust.setPrenos(1);
					
					dopustDAO.insert(dopust);
					dopustDAO.flush();
					
				} 
			}

			Integer zapadlost = dopust.getZapadlost() != null ? dopust.getZapadlost() : 0;
			
			if (zapadlost == 0 && datumZapadlosti.before(trenutniDatum)) {

				dopust = dopustDAO.merge(dopust);
				
				Integer zapadel = (dopust.getPreneseniDopust() - dopust.getIzkorisceniDopust()) < 0 ? 0 : dopust.getPreneseniDopust() - dopust.getIzkorisceniDopust(); 
				
				Integer novNeizkoriscen = dopust.getNeizkorisceniDopust();
				
				if (zapadel > 0) {
					novNeizkoriscen = dopust.getNeizkorisceniDopust() - (dopust.getPreneseniDopust() - dopust.getIzkorisceniDopust());
				}
				
				dopust.setZapadelDopust(zapadel);
				dopust.setLeto(Calendar.getInstance().get(Calendar.YEAR));
				dopust.setNeizkorisceniDopust(novNeizkoriscen);
				dopust.setZapadlost(1);
				
				dopustDAO.insert(dopust);
				dopustDAO.flush();
			}
		}
		
		List<Dan> dnevi = new ArrayList<Dan>();

		for (int i = min; i <= max; i++) {
			Dan dan = new Dan();
			dan.setSt(i);
			dan.setIme(dayFormat.format(temp.getTime()));
			dan.setDatum(temp.getTime());
			
			if (temp.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || temp.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				dan.setVikend(true);
			}
			
			for (Praznik praznik : praznikList) {
				if (praznik.getDatum().getTime() == dan.getDatum().getTime() && praznik.getProstiDan().intValue() == 1) {
					dan.setPraznik(praznik);
				}
			}
			
			temp.add(Calendar.DAY_OF_MONTH, 1);
			dnevi.add(dan);
		}
		
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("dnevi", dnevi);
		mv.addObject("zaposleniList", zaposleniList);
		mv.addObject("odsotnostRazlogList", odsotnostRazlogList);
		mv.addObject("mesec", monthFormat.format(calendar.getTime()));
		temp2.add(Calendar.MONTH, -1);
		mv.addObject("pl", temp2.get(Calendar.YEAR));
		mv.addObject("pm", temp2.get(Calendar.MONTH));
		temp2.add(Calendar.MONTH, 2);
		mv.addObject("nl", temp2.get(Calendar.YEAR));
		mv.addObject("nm", temp2.get(Calendar.MONTH));
		mv.addObject("leto", leto);
		mv.setViewName("admin/bugi-tracker");
		
		return mv;
	}
	
	@RequestMapping(value="/admin/azurirajOdsotnost", method=RequestMethod.POST)
	public @ResponseBody String azurirajOdsotnost(@RequestParam(value="zaposleniId", required=true)Integer zaposleniId, @RequestParam(value="razlogId", required=true)Integer razlogId, @RequestParam(value="dan", required=true)Integer dan) {
		log.debug("azurirajOdsotnost()");
		log.debug("zaposleniId = " + zaposleniId + " razlogId = " + razlogId + " dan = " + dan);
		
		Calendar temp = (Calendar)calendar.clone();
		temp.set(Calendar.DAY_OF_MONTH, dan);
		
		odsotnostDAO.delete(temp.getTime(), zaposleniId);
		
		if (razlogId != -1) {
			
			OdsotnostId oId = new OdsotnostId(razlogId, zaposleniId, temp.getTime());
			odsotnostDAO.insert(new Odsotnost(oId));
			odsotnostDAO.flush();
		} 
		
		Calendar letoStart = (Calendar)calendar.clone();
		letoStart.set(letoStart.get(Calendar.YEAR), 0, 1, 0, 0, 0);
		
		Calendar letoEnd = (Calendar)calendar.clone();
		letoEnd.set(letoEnd.get(Calendar.YEAR), 11, 31, 0, 0, 0);
		
		Long stDniDopusta = odsotnostDAO.getSumDopust(zaposleniId, letoStart.getTime(), letoEnd.getTime());
		
		dopust = dopustDAO.getDopust(zaposleniId, Calendar.getInstance().get(Calendar.YEAR));
		
		if(dopust == null) {
			setDefaultDopust(zaposleniId);
		}
		
		if (stDniDopusta.intValue() > dopust.getIzkorisceniDopust()) {
			
			dopust = dopustDAO.merge(dopust);
			
			dopust.setIdZaposleni(zaposleniId);
			dopust.setIzkorisceniDopust(stDniDopusta.intValue());
			dopust.setNeizkorisceniDopust(dopust.getNeizkorisceniDopust() - 1);
			dopust.setLeto(Calendar.getInstance().get(Calendar.YEAR));
			
			dopustDAO.insert(dopust);
			dopustDAO.flush();
		
		} else if (stDniDopusta.intValue() < dopust.getIzkorisceniDopust()) {

			dopust = dopustDAO.merge(dopust);
			
			//trenutniDatum.set((Calendar.getInstance().get(Calendar.YEAR)), 6, 2, 0, 0, 0);
			
			if (datumZapadlosti.before(trenutniDatum) && dopust.getRedniDopust() == dopust.getNeizkorisceniDopust()) {
				dopust.setZapadelDopust(dopust.getZapadelDopust() + 1);
			} else {
				dopust.setNeizkorisceniDopust(dopust.getNeizkorisceniDopust() + 1);
			}
			
			dopust.setIdZaposleni(zaposleniId);
			dopust.setIzkorisceniDopust(stDniDopusta.intValue());
			dopust.setLeto(Calendar.getInstance().get(Calendar.YEAR));
			
			dopustDAO.insert(dopust);
			dopustDAO.flush();
		} 
		
		return "1";
	}
	
	@RequestMapping(value="/admin/novKomentar", method=RequestMethod.POST)
	public ModelAndView novKomentar(@RequestParam(value="zaposleniId", required=true)Integer zaposleniId, @RequestParam(value="razlogId", required=true)Integer razlogId,  @RequestParam(value="dan", required=true)Integer dan) {
		log.debug("novKomentar()");
		log.debug("zaposleniId = " + zaposleniId + " razlogId = " + razlogId + " dan = " + dan);
		
		Calendar temp = (Calendar)calendar.clone();
		temp.set(Calendar.DAY_OF_MONTH, dan);
		
		odsotnost = odsotnostDAO.getOdsotnost(zaposleniId, temp.getTime());
		if (odsotnost == null) {
			odsotnost = new Odsotnost(new OdsotnostId(razlogId, zaposleniId, temp.getTime()));
		}
		
		ModelAndView mav = new ModelAndView();
			
		mav.addObject("odsotnost", odsotnost);
		mav.setViewName("admin/includes/ajax/dodaj-komentar-dialog");
		return mav;
	}
	
	@RequestMapping(value="/admin/shraniKomentar", method=RequestMethod.POST)
	public @ResponseBody String shraniKomentar(@RequestParam(value="razlogId")Integer razlogId, @RequestParam(value="komentar")String komentar) {
		log.debug("shraniKomentar()");
		log.debug("komentar = " + komentar);
		
		log.debug(odsotnost);
		
		odsotnost = odsotnostDAO.merge(odsotnost);
		odsotnost.setRazlogId(razlogId);
		odsotnost.setKomentar(komentar);
		odsotnostDAO.insert(odsotnost);
		odsotnostDAO.flush();
		
		return "1";
	}
	
	@RequestMapping(value="/admin/urejanjeDopusta", method=RequestMethod.POST)
	public ModelAndView urejanjeDopusta(@RequestParam(value="zaposleniId", required=true)Integer zaposleniId, @RequestParam(value="leto", required=true)Integer leto) {
		log.debug("urejanjeDopusta()");
		
		dopust = dopustDAO.getDopust(zaposleniId, leto);
		
		if(dopust == null) {
			setDefaultDopust(zaposleniId);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("dopust", dopust);
		mav.setViewName("admin/includes/ajax/uredi-dopust-dialog");
		
		return mav;
	}
	
	@RequestMapping(value="/admin/shraniDopust", method=RequestMethod.POST)
	public String shraniDopust(@RequestParam(value="zaposleniId", required=true)Integer zaposleniId, @RequestParam(value="preneseni", required=true)Integer preneseni, @RequestParam(value="redni", required=true)Integer redni, @RequestParam(value="neizkorisceni", required=true)Integer neizkorisceni, @RequestParam(value="izkorisceni", required=true)Integer izkorisceni, @RequestParam(value="leto", required=true)Integer leto, @RequestParam(value="zapadelDopust", required=true)Integer zapadelDopust) {
		log.debug("shraniDopust()");
		
		dopust = dopustDAO.merge(dopust);
		
		dopust.setIdZaposleni(zaposleniId);
		dopust.setPreneseniDopust(preneseni);
		dopust.setRedniDopust(redni);
		dopust.setNeizkorisceniDopust(neizkorisceni);
		dopust.setIzkorisceniDopust(izkorisceni);
		dopust.setLeto(leto);
		dopust.setZapadelDopust(zapadelDopust);
		
		dopustDAO.insert(dopust);
		dopustDAO.flush();
		
		return "1";
	}
	
	public void setDefaultDopust(Integer zaposleniId) {
		
		dopust = new Dopust();
		
		dopust.setIdZaposleni(zaposleniId);
		dopust.setPreneseniDopust(0);
		dopust.setRedniDopust(0);
		dopust.setNeizkorisceniDopust(0);
		dopust.setIzkorisceniDopust(0);
		dopust.setLeto(0);
		dopust.setZapadelDopust(0);
		dopust.setZapadlost(0);
		dopust.setPrenos(0);
	}
}
