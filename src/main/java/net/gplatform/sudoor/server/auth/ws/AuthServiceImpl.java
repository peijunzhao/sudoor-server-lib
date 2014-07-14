package net.gplatform.sudoor.server.auth.ws;

import java.util.List;

import javax.jws.WebService;

import net.gplatform.sudoor.server.ss.CredentialUserRepository;
import net.gplatform.sudoor.server.ss.entity.CredentialAuthority;
import net.gplatform.sudoor.server.ss.entity.CredentialUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@WebService
public class AuthServiceImpl implements AuthService {
	@Autowired
	net.gplatform.sudoor.server.auth.SSAuthentication SSAuthentication;

	@Autowired
	private CredentialUserRepository credentialUserRepository;

	@Override
	public String authenticate(String username, String password) {
		SSAuthentication.authenticate(username, password);
		return "SUCCESS";
	}

	@Override
	public String register(String username, String password) {
		CredentialUser credentialUser = new CredentialUser();
		credentialUser.setUsername(username);
		credentialUser.setPassword(password);
		credentialUser.setEnabled(true);
		
		CredentialAuthority credentialAuthority = new CredentialAuthority();
		credentialAuthority.setUsername(username);
		credentialAuthority.setAuthority("ROLE_USER");
		
		//credentialUser.setAuthorities(credentialAuthority);
		List<CredentialUser> l = credentialUserRepository.findAll();
		for(CredentialUser cu:l){
			System.out.println("=====================u"+cu.getUsername());
			System.out.println("=====================p"+cu.getPassword());
		}
		
		credentialUserRepository.saveAndFlush(credentialUser);
		return "SUCCESS";
	}
}
