package w19d1esercizio.dispositivi;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.AttributeConverter;

public class SistemaSicurezzaConverter implements AttributeConverter<String, String> {
	@Value("${ALGORITHM}")
	private String algorithm;

	@Value("${ENCRIPTION_SECRET}")
	private String encriptionSecret;

	@Override
	public String convertToDatabaseColumn(String DataToEncript) {
		try {
			Key key = new SecretKeySpec(encriptionSecret.getBytes(), "AES");
			Cipher c = Cipher.getInstance(algorithm);
			c.init(Cipher.ENCRYPT_MODE, key);
			return Base64.getEncoder().encodeToString(c.doFinal(DataToEncript.getBytes()));

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public String convertToEntityAttribute(String encriptedData) {
		try {
			Key key = new SecretKeySpec(encriptionSecret.getBytes(), "AES");
			Cipher c = Cipher.getInstance(algorithm);
			c.init(Cipher.DECRYPT_MODE, key);
			return new String(c.doFinal(Base64.getDecoder().decode(encriptedData.getBytes())));

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
