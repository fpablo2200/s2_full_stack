package semana2.demo.service;

import semana2.demo.model.Pelicula;
import semana2.demo.repository.PeliculaRepository;
import semana2.demo.exception.PeliculaNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository repositorio;

    public PeliculaService(PeliculaRepository peliculaRepositorio) {
        this.repositorio = peliculaRepositorio;
}

    public List<Pelicula> obtenerPeliculas(){
        log.debug("Servicio: obtenerPeliculas()");
        return repositorio.findAll(Sort.by("id").ascending());
    }

    public Pelicula obtenerPeliculaId(Long id){
        log.debug("Servicio: obtenerPeliculaId({})", id);
        return repositorio.findById(id)
                        .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula guardarPelicula(Pelicula pelicula){
        log.debug("Servicio: guardarPelicula({})", pelicula.getTitulo());

        if (repositorio.existsById(pelicula.getId())) {
                log.error("Ya existe una película con ID {}", pelicula.getId());
                throw new IllegalArgumentException("Ya existe una película con ID " + pelicula.getId());
        }
        return repositorio.save(pelicula);
    }

    public void eliminar(Long id){
        log.debug("Servicio: eliminar({})", id);

        Pelicula existente = repositorio.findById(id)
                        .orElseThrow(() -> new PeliculaNotFoundException(id));

        repositorio.delete(existente);
    }

    public Pelicula actualizar(Long id, Pelicula peliculaAct) {
        log.debug("Servicio: actualizar({}, {})", id, peliculaAct.getTitulo());

        Pelicula existente = repositorio.findById(id)
                        .orElseThrow(() -> new PeliculaNotFoundException(id));

        existente.setTitulo(peliculaAct.getTitulo());
        existente.setAno(peliculaAct.getAno());
        existente.setDirector(peliculaAct.getDirector());
        existente.setGenero(peliculaAct.getGenero());
        existente.setSinopsis(peliculaAct.getSinopsis());

        return repositorio.save(existente);
    }
}
