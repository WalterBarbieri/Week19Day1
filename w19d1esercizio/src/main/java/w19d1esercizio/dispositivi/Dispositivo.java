package w19d1esercizio.dispositivi;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import w19d1esercizio.utenti.Utente;

@Entity
@Table(name = "dispositivi")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({ "numeroSicurezza" })
public class Dispositivo {

	@Id
	@GeneratedValue
	private UUID id;
	@Enumerated(EnumType.STRING)
	private TipoDispositivo tipo;
	@Enumerated(EnumType.STRING)
	private StatoDispositivo stato;
	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;

	@Convert(converter = SistemaSicurezzaConverter.class)
	private String numeroSicurezza;

	public Dispositivo(TipoDispositivo tipo, StatoDispositivo stato, String numeroSicurezza) {
		this.setTipo(tipo);
		this.setStato(stato);
		this.setNumeroSicurezza(numeroSicurezza);
	}

	@Override
	public String toString() {
		return "DispositivoRequestPayload [Tipo: " + tipo + ", Stato: " + stato + ", Utente: " + utente.getUsername()
				+ ", Id: " + utente.getId() + "]";
	}

}
