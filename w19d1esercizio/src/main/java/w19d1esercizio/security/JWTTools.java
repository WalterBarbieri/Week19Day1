package w19d1esercizio.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import w19d1esercizio.exceptions.UnauthorizeException;
import w19d1esercizio.utenti.Utente;

@Component
public class JWTTools {
	@Value("${spring.jwt.secret}")
	private String secret;

	public String createToken(Utente u) {
		String token = Jwts.builder().setSubject(u.getId().toString()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();

		return token;
	}

	public void verifyToken(String Token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(Token);
		} catch (Exception e) {
			throw new UnauthorizeException("Token non valido, effettuare nuovamente il login");
		}
	}

	public String extractSubject(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject();
	}
}
