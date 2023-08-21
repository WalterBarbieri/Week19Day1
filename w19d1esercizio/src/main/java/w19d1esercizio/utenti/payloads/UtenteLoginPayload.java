package w19d1esercizio.utenti.payloads;

import lombok.Data;

@Data
public class UtenteLoginPayload {
	private String email;
	private String password;
}
