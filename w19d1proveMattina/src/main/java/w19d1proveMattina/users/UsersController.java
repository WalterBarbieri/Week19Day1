package w19d1proveMattina.users;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import w19d1proveMattina.users.payloads.UserRequestPayload;

@RestController
@RequestMapping("/users")
public class UsersController {

	private final UsersService us;

	@Autowired
	public UsersController(UsersService us) {
		this.us = us;
	}

	@GetMapping
	public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		return us.find(page, size, sortBy);
	}

	@GetMapping("/{userId}")
	public User findById(@PathVariable UUID userId) {
		return us.findById(userId);
	}

	@PutMapping("/{userId}")
	public User updateUser(@PathVariable UUID userId, @RequestBody UserRequestPayload body) {
		return us.findByIdAndUpdate(userId, body);

	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId) {
		us.findByIdAndDelete(userId);
	}

}
