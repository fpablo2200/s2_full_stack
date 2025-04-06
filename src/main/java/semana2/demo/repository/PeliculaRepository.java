package semana2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import semana2.demo.model.Pelicula;;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
    
}
