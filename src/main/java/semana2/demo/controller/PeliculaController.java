package semana2.demo.controller;

import semana2.demo.model.Pelicula;
import semana2.demo.service.PeliculaService;
import semana2.demo.model.ResponseWrapper;
import semana2.demo.hateoas.PeliculaModelAssembler;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Slf4j
@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;
    private final PeliculaModelAssembler assembler;

    public PeliculaController(PeliculaService peliculaService, PeliculaModelAssembler assembler){
        this.peliculaService = peliculaService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Pelicula>>> obtenerTodas() {
        log.info("GET /peliculas - Obteniendo todas las películas");

        List<Pelicula> peliculas = peliculaService.obtenerPeliculas();

        if (peliculas.isEmpty()) {
            log.warn("No hay películas registradas actualmente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CollectionModel.empty());
        }

        List<EntityModel<Pelicula>> modelos = peliculas.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos,
            linkTo(methodOn(PeliculaController.class).obtenerTodas()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pelicula>> obtenerPorId(@PathVariable Long id) {
        log.info("GET /peliculas/{} - Buscando película por ID", id);
        Pelicula pelicula = peliculaService.obtenerPeliculaId(id);

        return ResponseEntity.ok(assembler.toModel(pelicula));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Pelicula>> gdrPelicula(@RequestBody Pelicula pelicula) {
        log.info("POST /peliculas - Creando película: {}", pelicula.getTitulo());

        Pelicula creada = peliculaService.guardarPelicula(pelicula);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(creada));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Pelicula>> actualizarPelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        log.info("PUT /peliculas/{} - Actualizando película", id);
        Pelicula actualizada = peliculaService.actualizar(id, pelicula);

        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarRegistro(@PathVariable Long id){
        log.warn("DELETE /peliculas/{} - Eliminando película", id);
        peliculaService.eliminar(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Película eliminada exitosamente",
                        0,
                        null));
    }


}
