package w19d1esercizio.dispositivi;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import w19d1esercizio.dispositivi.payloads.DispositivoRequestPayload;
import w19d1esercizio.exceptions.NotFoundException;
import w19d1esercizio.utenti.Utente;
import w19d1esercizio.utenti.UtenteService;

@Service
public class DispositivoService {

	@Autowired
	private DispositivoRepository dr;

	@Autowired
	private UtenteService us;

	public Dispositivo creaDispositivo(DispositivoRequestPayload body) {
		Dispositivo dispositivo = new Dispositivo(body.getTipo(), body.getStato());
		if (body.getUtenteId() != null) {
			Utente utente = us.findById(body.getUtenteId());
			dispositivo.setUtente(utente);
		}
		return dr.save(dispositivo);
	}

	public Page<Dispositivo> findDispositivi(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return dr.findAll(pageable);
	}

	public Dispositivo findById(UUID id) {
		return dr.findById(id)
				.orElseThrow(() -> new NotFoundException("Nessun dispositivo corrispondente a " + id + " trovato"));
	}

	public Dispositivo findByIdAndUpdate(UUID id, DispositivoRequestPayload body) {
		Dispositivo dispositivo = this.findById(id);
		dispositivo.setTipo(body.getTipo());
		dispositivo.setStato(body.getStato());
		if (body.getUtenteId() != null) {
			Utente utente = us.findById(body.getUtenteId());
			dispositivo.setUtente(utente);
		}

		return dr.save(dispositivo);
	}

	public void deleteDispositivo(UUID id) {
		Dispositivo dispositivo = this.findById(id);
		dr.delete(dispositivo);
	}

	public Dispositivo changeUtente(UUID id, UUID utenteId) {
		Dispositivo dispositivo = this.findById(id);
		if (utenteId != null) {
			Utente utente = us.findById(utenteId);
			dispositivo.setUtente(utente);
		} else {
			dispositivo.setUtente(null);
		}
		return dr.save(dispositivo);
	}

	public Dispositivo changeStato(UUID id, StatoDispositivo stato) {
		Dispositivo dispositivo = this.findById(id);
		dispositivo.setStato(stato);
		return dr.save(dispositivo);
	}

	public List<Dispositivo> findByUtenteId(UUID utenteId) {
		return dr.findAllByUtenteId(utenteId);
	}

}
