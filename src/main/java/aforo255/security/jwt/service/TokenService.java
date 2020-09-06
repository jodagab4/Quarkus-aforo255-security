package aforo255.security.jwt.service;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;

import org.eclipse.microprofile.jwt.Claims;
import org.jboss.logging.Logger;
import org.jose4j.jwt.JwtClaims;

import aforo255.security.jwt.token.TokenUtils;

@RequestScoped
public class TokenService {

	public final static Logger LOGGER = Logger.getLogger(TokenService.class);
	public String generarToken(String username, String email) {
		try {
			JwtClaims jwtClaims = new JwtClaims();	
			jwtClaims.setIssuer("aforo255");
			jwtClaims.setJwtId(UUID.randomUUID().toString());
			jwtClaims.setSubject(email);
			jwtClaims.setClaim(Claims.upn.name(),email);
			jwtClaims.setClaim(Claims.upn.preferred_username.name(),username);
			jwtClaims.setClaim(Claims.upn.groups.name(),Arrays.asList("User","Admin"));
			jwtClaims.setAudience("using-jwt");
			jwtClaims.setExpirationTimeMinutesInTheFuture(60);
			
			String token = TokenUtils.generateTokenString(jwtClaims);
			LOGGER.info("TOKEN generated: " + token);
			return token;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
}
