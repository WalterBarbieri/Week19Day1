package w19d1esercizio.dispositivi;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, UUID> {
	List<Dispositivo> findAllByUtenteId(UUID utenteId);
}
