package w19d1proveMattina.users.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class UserRequestPayload {
	private String name;
	private String surname;
	private String email;
	private String password;
	private String creditCard;
}
