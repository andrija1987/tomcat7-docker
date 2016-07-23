package si.asoft.bugitracker.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import si.asoft.bugitracker.jpa.Zaposleni;

@Service
public class CredentialStore {
	public Zaposleni getUser() {
		Zaposleni zaposleni = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		if (principal instanceof AuthenticatedUser) {
			zaposleni = ((AuthenticatedUser) principal).getZaposleni();
		}
		return zaposleni;
	}
}
