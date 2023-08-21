package w19d1esercizio.dispositivi;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import w19d1esercizio.dispositivi.payloads.DispositivoRequestPayload;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {
	@Autowired
	private DispositivoService ds;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Dispositivo creaDispositivo(@RequestBody DispositivoRequestPayload body) {
		return ds.creaDispositivo(body);
	}

	@GetMapping
	public Page<Dispositivo> findDispositivi(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return ds.findDispositivi(page, size, sortBy);
	}

	@GetMapping("/{id}")
	public Dispositivo findById(@PathVariable UUID id) {
		return ds.findById(id);
	}

	@PutMapping("/{id}")
	public Dispositivo findByIdAndUpdate(@PathVariable UUID id, @RequestBody DispositivoRequestPayload body) {
		return ds.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDispositivo(@PathVariable UUID id) {
		ds.deleteDispositivo(id);
	}

	@PatchMapping("/{id}/utenti")
	public Dispositivo changeUtente(@PathVariable UUID id, @RequestParam(required = false) UUID utenteId) {
		return ds.changeUtente(id, utenteId);
	}

	@PatchMapping("/{id}")
	public Dispositivo changeStato(@PathVariable UUID id, @RequestParam StatoDispositivo stato) {
		return ds.changeStato(id, stato);
	}

}
