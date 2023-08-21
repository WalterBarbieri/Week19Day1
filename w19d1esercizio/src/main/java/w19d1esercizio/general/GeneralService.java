package w19d1esercizio.general;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w19d1esercizio.dispositivi.Dispositivo;
import w19d1esercizio.dispositivi.DispositivoService;
import w19d1esercizio.utenti.Utente;
import w19d1esercizio.utenti.UtenteRepository;
import w19d1esercizio.utenti.UtenteService;

@Service
public class GeneralService {
	private final UtenteService us;
	private final DispositivoService ds;
	private final UtenteRepository ur;

	@Autowired
	public GeneralService(UtenteService us, DispositivoService ds, UtenteRepository ur) {
		this.us = us;
		this.ds = ds;
		this.ur = ur;
	}

	public void deleteUtente(UUID id) {
		Utente utente = us.findById(id);
		List<Dispositivo> dispositivi = ds.findByUtenteId(id);
		if (!dispositivi.isEmpty()) {
			for (Dispositivo dispositivo : dispositivi) {
				dispositivo.setUtente(null);
			}
		}
		ur.delete(utente);
	}
}
