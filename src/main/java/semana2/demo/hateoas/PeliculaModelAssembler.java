package semana2.demo.hateoas;

import semana2.demo.controller.PeliculaController;
import semana2.demo.model.Pelicula;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; 
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PeliculaModelAssembler implements RepresentationModelAssembler<Pelicula, EntityModel<Pelicula>> {
    
    @Override
    public EntityModel<Pelicula> toModel(Pelicula pelicula) {
        return EntityModel.of(
                pelicula, 

                linkTo(methodOn(PeliculaController.class)
                        .obtenerPorId(pelicula.getId()))
                        .withSelfRel(),

                linkTo(methodOn(PeliculaController.class)
                        .eliminarRegistro(pelicula.getId()))
                        .withRel("delete"),

                linkTo(methodOn(PeliculaController.class)
                        .actualizarPelicula(pelicula.getId(), null))
                        .withRel("update"),

                linkTo(methodOn(PeliculaController.class)
                        .obtenerTodas())
                        .withRel("all"));
    }
}
