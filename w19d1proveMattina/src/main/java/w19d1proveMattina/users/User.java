package w19d1proveMattina.users;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({ "password", "authorities", "enabled", "credentialsNonExpired", "accountNonExpired",
		"accountNonLocked" })
public class User implements UserDetails {
	@Id
	@GeneratedValue
	private UUID id;
	private String name;
	private String surname;
	@Column(unique = true)
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Convert(converter = CreditCardConverter.class)
	private String creditCard;

	public User(String name, String surname, String email, String password, String creditCard) {
		this.setName(name);
		this.setSurname(surname);
		this.setEmail(email);
		this.setPassword(password);
		this.role = Role.USER;
		this.creditCard = creditCard;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		// USername, email etc...
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// Metti sempre true
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// Metti sempre true
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// Metti sempre true
		return true;
	}

	@Override
	public boolean isEnabled() {
		// Metti sempre true
		return true;
	}
}
