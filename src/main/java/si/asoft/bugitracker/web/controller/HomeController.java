package si.asoft.bugitracker.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import si.asoft.bugitracker.jpa.Dopust;
import si.asoft.bugitracker.jpa.OdsotnostRazlog;
import si.asoft.bugitracker.jpa.Praznik;
import si.asoft.bugitracker.jpa.Zaposleni;
import si.asoft.bugitracker.jpa.dao.DopustDAO;
import si.asoft.bugitracker.jpa.dao.OdsotnostDAO;
import si.asoft.bugitracker.jpa.dao.OdsotnostRazlogDAO;
import si.asoft.bugitracker.jpa.dao.PraznikDAO;
import si.asoft.bugitracker.jpa.dao.ZaposleniDAO;
import si.bojanbajc.menza.web.beans.Dan;
import si.bojanbajc.menza.web.beans.Mesec;

/**
 * Sample controller for going to the home page with a message
 */
@Controller
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	private Calendar calendar;
	private Locale locale;
	
	@Autowired ZaposleniDAO zaposleniDAO;
	@Autowired OdsotnostRazlogDAO odsotnostRazlogDAO;
	@Autowired OdsotnostDAO odsotnostDAO;
	@Autowired PraznikDAO praznikDAO;
	
	@Autowired DopustDAO dopustDAO;
	
	@RequestMapping(value="/users/home", method=RequestMethod.GET) 
	public ModelAndView startForm() {
		log.debug("startForm()");
		
		if (locale == null) {
			locale = new Locale("sl");
		}
		calendar = new GregorianCalendar(locale);
		return startForm(calendar.get(Calendar.YEAR));
	}

	@RequestMapping(value="/users/home/{leto}", method=RequestMethod.GET)
	public ModelAndView startForm(@PathVariable Integer leto) {
		log.debug("startForm()");
		if (calendar == null) {
			locale = new Locale("sl");
			calendar = new GregorianCalendar(locale);
		}
		if (leto < 2000) {
			leto = 2000;
		}
	
		calendar.set(leto, 0, 1, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return getKoledar();
	}
	
	private ModelAndView getKoledar() {
		
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", locale);
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", locale);
		SimpleDateFormat dayFormat = new SimpleDateFormat("EEE", locale); 

		Calendar temp = (Calendar)calendar.clone();
		Calendar temp2 = (Calendar)calendar.clone();
		
		Calendar letoStart = (Calendar)calendar.clone();
		letoStart.set(letoStart.get(Calendar.YEAR), 0, 1, 0, 0, 0);
		
		Calendar letoEnd = (Calendar)calendar.clone();
		letoEnd.set(letoEnd.get(Calendar.YEAR), 11, 31, 0, 0, 0);
	
		Zaposleni zaposleni = getZaposleni();
		zaposleni.setStDniDopusta(odsotnostDAO.getSumDopust(zaposleni.getId(), letoStart.getTime(), letoEnd.getTime()));
		List<OdsotnostRazlog> odsotnostRazlogList = odsotnostRazlogDAO.getOdsotnostRazlogList();

		Dopust dopust = dopustDAO.getDopust(zaposleni.getId(), Calendar.getInstance().get(Calendar.YEAR));
		Dopust dopustLanski = dopustDAO.getDopust(zaposleni.getId(), Calendar.getInstance().get(Calendar.YEAR)-1);
		
		List<Praznik> praznikList = praznikDAO.getPraznikList((short)calendar.get(Calendar.YEAR));
				
		List<Mesec> mesci = new ArrayList<Mesec>();

		for (int i = 0; i <= 11; i++) {
			
			String m = monthFormat.format(temp.getTime());
			List<Dan> dnevi = new ArrayList<Dan>();
			
			Mesec mesec = new Mesec(m, dnevi);
			
			int min = temp.getActualMinimum(Calendar.DAY_OF_MONTH);
			int max = temp.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			for (int j = min; j <= max; j++) {
				Dan dan = new Dan();
				dan.setSt(j);
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
			
			while (max != 31) {
				Dan dan = new Dan();
				dan.setSt(null);
				dan.setIme("");
				dan.setDatum(null);
				max++;
				dnevi.add(dan);
			}
		
			mesci.add(mesec);
		}
			
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("mesci", mesci);
		mv.addObject("zaposleni", zaposleni);
		mv.addObject("odsotnostRazlogList", odsotnostRazlogList);
		mv.addObject("leto", yearFormat.format(calendar.getTime()));
		temp2.add(Calendar.YEAR, -1);
		mv.addObject("pl", temp2.get(Calendar.YEAR));
		temp2.add(Calendar.YEAR, 2);
		mv.addObject("nl", temp2.get(Calendar.YEAR));
		mv.addObject("dopust", dopust);
		if (dopustLanski != null) {
			mv.addObject("dopustLanski", dopustLanski);
		}
		mv.setViewName("users/home");
		
		return mv;
	}
	
	private Zaposleni getZaposleni() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		return zaposleniDAO.getZaposleni(userName);
	}
}
