package semana2.demo.service;

import semana2.demo.model.Pelicula;
import semana2.demo.repository.PeliculaRepository;
import semana2.demo.exception.PeliculaNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository repositorio;

    public List<Pelicula> obtenerPeliculas(){
        return repositorio.findAll();
    }

    public Pelicula obtenerPeliculaId(Long id){
        return repositorio.findById(id).orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula guardarPelicula(Pelicula pelicula){
        return repositorio.save(pelicula);
    }

    public void eliminar(Long id){
        repositorio.deleteById(id);
    }
}
