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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




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

    @PostMapping
    public Pelicula gdrPelicula(@RequestBody Pelicula pelicula) {
        
        return peliculaService.guardarPelicula(pelicula);
    }
    
    @PutMapping("/{id}")
    public Pelicula actualizarPelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        Pelicula peliculaencontrada = peliculaService.obtenerPeliculaId(id);
        if(peliculaencontrada != null){
            peliculaencontrada.setTitulo(pelicula.getTitulo());
            peliculaencontrada.setAno(pelicula.getAno());
            peliculaencontrada.setDirector(pelicula.getDirector());
            peliculaencontrada.setGenero(pelicula.getGenero());
            peliculaencontrada.setSinopsis(pelicula.getSinopsis());
            return peliculaService.guardarPelicula(peliculaencontrada);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminarRegistro(@PathVariable Long id){
        peliculaService.eliminar(id);
    }


}
