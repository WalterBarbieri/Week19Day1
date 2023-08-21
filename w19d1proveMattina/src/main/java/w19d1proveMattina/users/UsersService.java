package w19d1proveMattina.users;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import w19d1proveMattina.exceptions.BadRequestException;
import w19d1proveMattina.exceptions.NotFoundException;
import w19d1proveMattina.users.payloads.UserRequestPayload;

@Service
public class UsersService {

	private final UsersRepository ur;

	@Autowired
	public UsersService(UsersRepository ur) {
		this.ur = ur;
	}

	public User create(UserRequestPayload body) {
		ur.findByEmail(body.getEmail()).ifPresent(user -> {
			throw new BadRequestException("Email già esistente");
		});
		User newUser = new User(body.getName(), body.getSurname(), body.getEmail(), body.getPassword(),
				body.getCreditCard());
		return ur.save(newUser);
	}

	public Page<User> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return ur.findAll(pageable);
	}

	public User findById(UUID userId) throws NotFoundException {
		return ur.findById(userId).orElseThrow(() -> new NotFoundException(userId));
	}

	public User findByIdAndUpdate(UUID userId, UserRequestPayload body) throws NotFoundException {
		User found = this.findById(userId);
		found.setEmail(body.getEmail());
		found.setName(body.getName());
		found.setSurname(body.getSurname());

		return ur.save(found);
	}

	public void findByIdAndDelete(UUID userId) throws NotFoundException {
		User found = this.findById(userId);
		ur.delete(found);
	}

	public User findByEmail(String email) {
		return ur.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Nessun utente corrispondente a " + email + " trovato"));
	}
}
