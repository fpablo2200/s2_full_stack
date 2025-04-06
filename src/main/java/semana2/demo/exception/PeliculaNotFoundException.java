package semana2.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PeliculaNotFoundException extends RuntimeException {
    public PeliculaNotFoundException(Long id) {
        // Generamos un mensaje personalizado que luego será visible en la respuesta de
        // error
        super("La película con id " + id + " no fue encontrada");
    }
}
