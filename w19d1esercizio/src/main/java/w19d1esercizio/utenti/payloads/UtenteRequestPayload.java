package w19d1esercizio.utenti.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class UtenteRequestPayload {
	private String username;
	private String nome;
	private String cognome;
	private String email;
	private String password;
}
