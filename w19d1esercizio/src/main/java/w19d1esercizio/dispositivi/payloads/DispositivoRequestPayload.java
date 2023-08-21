package w19d1esercizio.dispositivi.payloads;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import w19d1esercizio.dispositivi.StatoDispositivo;
import w19d1esercizio.dispositivi.TipoDispositivo;

@Getter
@Setter
@ToString
public class DispositivoRequestPayload {
	private TipoDispositivo tipo;
	private StatoDispositivo stato;
	private UUID utenteId;

	public DispositivoRequestPayload(TipoDispositivo tipo, StatoDispositivo stato) {
		this.tipo = tipo;
		this.stato = stato;
	}

}
