package semana2.demo.service;

import semana2.demo.model.Pelicula;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {
    private final List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaService(){
        peliculas.add(new Pelicula(1L,"Juegos del hambre", 2012 , "Gary ross", "accion" ,
             "Para demostrar su poder, el régimen del estado totalitario de Panem organiza cada año"));
        peliculas.add(new Pelicula(2L,"Avatar", 2009 , "James Cameron", "accion" ,
             "Para demostrar su poder, el régimen del estado totalitario de Panem organiza cada año"));
        peliculas.add(new Pelicula(3L,"Yo antes de ti", 2016 , "Thea Sharrock", "accion" ,
             "Para demostrar su poder, el régimen del estado totalitario de Panem organiza cada año"));
        peliculas.add(new Pelicula(4L,"Avengers: Endgame", 2019 , "Anthony Russo, Joe Russo",
             "accion" , "Para demostrar su poder, el régimen del estado totalitario de Panem organiza cada año"));
    
    
    
    }

    public List<Pelicula> obtenerPeliculas(){
        return peliculas;
    }

    public Optional<Pelicula> obtenerPeliculaId(Long id){
        return peliculas.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
