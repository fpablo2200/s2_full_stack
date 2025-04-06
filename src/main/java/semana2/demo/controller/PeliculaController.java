package semana2.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import semana2.demo.model.Pelicula;
import semana2.demo.service.PeliculaService;
import semana2.demo.model.ResponseWrapper;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService){
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        List<Pelicula> peliculas = peliculaService.obtenerPeliculas();

        if (peliculas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay películas registradas actualmente");
        }

        ResponseWrapper<Pelicula> respuesta = new ResponseWrapper<>(
                "OK",
                peliculas.size(),
                peliculas);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPorId(@PathVariable Long id) {

        return peliculaService.obtenerPeliculaId(id);
    }


}
