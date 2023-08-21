package w19d1esercizio.utenti;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import w19d1esercizio.exceptions.NotFoundException;
import w19d1esercizio.utenti.payloads.UtenteRequestPayload;

@Service
public class UtenteService {

	private final UtenteRepository ur;

	@Autowired
	public UtenteService(UtenteRepository ur) {

		this.ur = ur;

	}

	public Utente creaUtente(UtenteRequestPayload body) {
		Utente newUtente = new Utente(body.getUsername(), body.getNome(), body.getCognome(), body.getEmail(),
				body.getPassword());
		return ur.save(newUtente);
	}

	public Utente saveUtente(Utente utente) {
		return ur.save(utente);
	}

	public List<Utente> findAll() {
		return ur.findAll();
	}

	public Page<Utente> findUtenti(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return ur.findAll(pageable);
	}

	public Utente findByEmail(String email) {
		return ur.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Nessun utente corrispondente a " + email + " trovato"));
	}

	public Utente findById(UUID id) {
		return ur.findById(id)
				.orElseThrow(() -> new NotFoundException("Nessun utente corrispondente a " + id + " trovato"));
	}

	public Utente findByIdAndUpdate(UUID id, UtenteRequestPayload body) throws NotFoundException {
		Utente utente = this.findById(id);
		utente.setUsername(body.getUsername());
		utente.setNome(body.getNome());
		utente.setCognome(body.getCognome());
		utente.setEmail(body.getEmail());
		utente.setPassword(body.getPassword());
		return ur.save(utente);
	}

	public Utente rndUtente() {
		Random rnd = new Random();
		List<Utente> utenti = ur.findAll();
		if (utenti.isEmpty()) {
			return null;
		}
		return utenti.get(rnd.nextInt(utenti.size()));
	}

}
