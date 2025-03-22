package semana2.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import semana2.demo.model.Pelicula;
import semana2.demo.service.PeliculaService;

import java.util.List;
import java.util.Optional;

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
    public List<Pelicula> obtenerPeliculas(){
        return peliculaService.obtenerPeliculas();
    }

    @GetMapping("/{id}")
    public Optional<Pelicula> obtenerPeliculaId(@PathVariable Long id){
        return peliculaService.obtenerPeliculaId(id);
    }
}
