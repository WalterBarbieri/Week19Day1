package w19d1esercizio.utenti.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class UtenteRequestPayload {
	@NotNull(message = "Lo Username è obbligatorio")
	@Size(min = 5, max = 30, message = "Caratteri minimi 5, massimi 30")
	private String username;
	@NotNull(message = "Il nome è obbligatorio")
	private String nome;
	@NotNull(message = "Il cognome è obbligatorio")
	private String cognome;
	@NotNull(message = "L'email è obbligatoria")
	@Email(message = "L'email inserita non è un indirizzo corretto")
	private String email;
	@NotNull(message = "La password è obbligatoria")
	private String password;
}
