package w19d1esercizio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import w19d1esercizio.exceptions.UnauthorizeException;
import w19d1esercizio.utenti.Utente;
import w19d1esercizio.utenti.UtenteService;
import w19d1esercizio.utenti.payloads.UtenteLoginPayload;
import w19d1esercizio.utenti.payloads.UtenteRequestPayload;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UtenteService us;

	@Autowired
	private JWTTools jt;

	@Autowired
	private PasswordEncoder bcrypt;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Utente creaUtente(@RequestBody UtenteRequestPayload body) {
		String hashedPassword = bcrypt.encode(body.getPassword());
		System.out.println(hashedPassword);
		body.setPassword(hashedPassword);
		Utente utente = us.creaUtente(body);
		return utente;
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UtenteLoginPayload body) {
		Utente utente = us.findByEmail(body.getEmail());
		if (body.getPassword().equals(utente.getPassword())) {
			String token = jt.createToken(utente);
			System.out.println(token);
			return new ResponseEntity<>(token, HttpStatus.OK);
		} else {
			throw new UnauthorizeException("Errore durante il login, controllare le credenziali inserite");
		}
	}
}
