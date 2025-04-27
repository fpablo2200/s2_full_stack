package semana2.demo;

import semana2.demo.exception.PeliculaNotFoundException;
import semana2.demo.model.Pelicula;
import semana2.demo.repository.PeliculaRepository;
import semana2.demo.service.PeliculaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.data.domain.Sort;

public class PeliculaServiceTest {

    private PeliculaRepository peliculaRepository;

    private PeliculaService peliculaService;

    @BeforeEach
    public void setUp() {
        peliculaRepository = mock(PeliculaRepository.class);
        peliculaService = new PeliculaService(peliculaRepository);
    }

    @Test
    public void testObtenerTodas() {

        Pelicula p1 = new Pelicula(1L, "Pelicula 1", 2020, "Director 1", "Acción", "Sinopsis 1");
        Pelicula p2 = new Pelicula(2L, "Pelicula 2", 2021, "Director 2", "Comedia", "Sinopsis 2");
        Pelicula p3 = new Pelicula(3L, "Pelicula 3", 2022, "Director 3", "Drama", "Sinopsis 3");

        when(peliculaRepository.findAll(Sort.by("id").ascending())).thenReturn(Arrays.asList(p1, p2, p3));

        List<Pelicula> resultado = peliculaService.obtenerPeliculas();

        assertEquals(3, resultado.size());
        assertEquals("Pelicula 1", resultado.get(0).getTitulo());
        assertEquals("Pelicula 3", resultado.get(2).getTitulo());
    }

    @Test
    public void testObtenerPorId_existente() {

        Pelicula pelicula = new Pelicula(1L, "Pelicula Test", 2022, "Director X", "Drama", "Sinopsis");

        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));

        Pelicula resultado = peliculaService.obtenerPeliculaId(1L);

        assertEquals("Pelicula Test", resultado.getTitulo());
        assertEquals(2022, resultado.getAno());
        assertEquals("Director X", resultado.getDirector());
        assertEquals("Drama", resultado.getGenero());
        assertEquals("Sinopsis", resultado.getSinopsis());
    }

    @Test
    public void testObtenerPorId_noExistente() {

        when(peliculaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PeliculaNotFoundException.class, () -> {
            peliculaService.obtenerPeliculaId(99L);
        });
    }
    
}
