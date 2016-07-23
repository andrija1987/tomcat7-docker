package si.asoft.bugitracker.web.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import si.asoft.bugitracker.jpa.Zaposleni;
import si.asoft.bugitracker.jpa.dao.ZaposleniDAO;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	protected ZaposleniDAO zaposleniDAO = null;

	public UserDetails loadUserByUsername(String upoIme) throws UsernameNotFoundException {

		Zaposleni zaposleni = zaposleniDAO.getZaposleni(upoIme);

		if(zaposleni == null) {
			throw new UsernameNotFoundException("Username not found");
		}

		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl(zaposleni.getRole()));

		return new AuthenticatedUser(upoIme, zaposleni.getUpoGeslo(), true, true, true, true, authorities, zaposleni);
	}
}
