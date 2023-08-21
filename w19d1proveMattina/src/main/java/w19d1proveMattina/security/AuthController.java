package w19d1proveMattina.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import w19d1proveMattina.exceptions.UnauthorizeException;
import w19d1proveMattina.users.User;
import w19d1proveMattina.users.UsersService;
import w19d1proveMattina.users.payloads.UserLoginPayload;
import w19d1proveMattina.users.payloads.UserRequestPayload;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UsersService us;

	@Autowired
	private JWTTools jwttTools;

	@Autowired
	private PasswordEncoder bcrypt;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody UserRequestPayload body) {
		body.setPassword(bcrypt.encode(body.getPassword()));
		body.setCreditCard("123456789454");
		User userCreated = us.create(body);
		return userCreated;
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserLoginPayload body) {
		User user = us.findByEmail(body.getEmail());
		if (bcrypt.matches(body.getPassword(), user.getPassword())) {
			String token = jwttTools.createToken(user);
			System.out.println(token);
			return new ResponseEntity<>(token, HttpStatus.OK);
		} else {
			throw new UnauthorizeException("Credenziali non valide!");
		}

	}

}
