package w19d1proveMattina;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import w19d1proveMattina.users.UsersService;
import w19d1proveMattina.users.payloads.UserRequestPayload;

@Component
public class UsersRunner implements CommandLineRunner {

	@Autowired
	UsersService us;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		for (int i = 0; i < 30; i++) {
			String name = faker.name().firstName();
			String surname = faker.name().lastName();
			String email = faker.internet().emailAddress();
			String password = faker.lorem().characters(8, 15);
			String creditCard = faker.business().creditCardNumber();
			UserRequestPayload rndUser = new UserRequestPayload(name, surname, email, password, creditCard);
			// us.create(rndUser);
		}

	}

}
