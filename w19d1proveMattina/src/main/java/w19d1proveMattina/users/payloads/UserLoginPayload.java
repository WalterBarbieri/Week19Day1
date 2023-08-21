package w19d1proveMattina.users.payloads;

import lombok.Data;

@Data
public class UserLoginPayload {
	private String email;
	private String password;
}
