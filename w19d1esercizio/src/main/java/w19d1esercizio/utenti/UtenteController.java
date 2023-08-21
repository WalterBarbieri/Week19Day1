package w19d1esercizio.utenti;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import w19d1esercizio.general.GeneralService;
import w19d1esercizio.utenti.payloads.UtenteRequestPayload;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
	private final UtenteService us;
	private final GeneralService gs;

	@Autowired
	public UtenteController(UtenteService us, GeneralService gs) {
		this.us = us;
		this.gs = gs;
	}

	@GetMapping
	public Page<Utente> findUtenti(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return us.findUtenti(page, size, sortBy);
	}

	@GetMapping("/{id}")
	public Utente findById(@PathVariable UUID id) {
		return us.findById(id);
	}

	@PutMapping("/{id}")
	public Utente findByIdAndUpdate(@PathVariable UUID id, @RequestBody UtenteRequestPayload body) {
		return us.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUtente(@PathVariable UUID id) {
		gs.deleteUtente(id);
	}

}
