package w19d1esercizio.runners;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import w19d1esercizio.dispositivi.DispositivoService;
import w19d1esercizio.dispositivi.StatoDispositivo;
import w19d1esercizio.dispositivi.TipoDispositivo;
import w19d1esercizio.dispositivi.payloads.DispositivoRequestPayload;
import w19d1esercizio.utenti.Utente;
import w19d1esercizio.utenti.UtenteService;
import w19d1esercizio.utenti.payloads.UtenteRequestPayload;

@Component
public class GeneralRunner implements CommandLineRunner {

	@Autowired
	UtenteService us;
	@Autowired
	DispositivoService ds;

	@Autowired
	PasswordEncoder bcrypt;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker();

		for (int i = 0; i < 50; i++) {
			String username = faker.ancient().god();
			String nome = faker.name().firstName();
			String cognome = faker.name().lastName();
			String email = faker.internet().emailAddress();
			String password = faker.lorem().characters(6, 15);
			String hashedPassword = bcrypt.encode(password);
			UtenteRequestPayload rndUtente = new UtenteRequestPayload(username, nome, cognome, email, hashedPassword);
			// us.creaUtente(rndUtente);
		}

		for (int i = 0; i < 150; i++) {
			Random rnd = new Random();
			TipoDispositivo rndTipo = TipoDispositivo.values()[rnd.nextInt(TipoDispositivo.values().length)];
			StatoDispositivo rndStato = StatoDispositivo.values()[rnd.nextInt(StatoDispositivo.values().length)];
			DispositivoRequestPayload rndDispositivo = new DispositivoRequestPayload(rndTipo, rndStato);
			int rndInt = rnd.nextInt(100);
			if (rndInt > 20) {
				Utente rndUtente = us.rndUtente();
				rndDispositivo.setUtenteId(rndUtente.getId());
			}
			// ds.creaDispositivo(rndDispositivo);

		}
		// Metodo per crittografare password preesistenti
//		List<Utente> utenti = us.findAll();
//
//		for (Utente utente : utenti) {
//			String hashedPassword = utente.getPassword();
//			if (!hashedPassword.startsWith("$2a$11$") || hashedPassword.length() != 60) {
//				String newHashedPassword = bcrypt.encode(hashedPassword);
//				utente.setPassword(newHashedPassword);
//				us.saveUtente(utente);
//
//			}
//		}

	}

}
