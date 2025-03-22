package semana2.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {
    private Long id;
    private String titulo;
    private int ano;
    private String director;
    private String genero;
    private String sinopsis; 
    
}
