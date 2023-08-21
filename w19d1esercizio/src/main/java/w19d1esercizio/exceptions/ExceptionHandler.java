package w19d1esercizio.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorsPayload handleBadRequest(BadRequestException e) {
		return new ErrorsPayload(e.getMessage(), new Date());
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorsPayload handleNotFound(NotFoundException e) {
		return new ErrorsPayload(e.getMessage(), new Date());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ErrorsPayload handleGeneric(Exception e) {
		log.error(e.getMessage());
		return new ErrorsPayload("Errore generico, risolveremo il prima possibile", new Date());
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizeException.class)
	public ErrorsPayload handleUnauthorized(UnauthorizeException e) {
		log.error(e.getMessage());
		return new ErrorsPayload("Errore, non sei autorizzato", new Date());
	}
}
