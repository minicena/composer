package br.com.composer.composer.repository;

import br.com.composer.composer.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    
    // Busca por nome exato (case insensitive)
    Genre findByNameIgnoreCase(String name);
    
    // Busca os gêneros mais populares (com mais faixas)
    @Query("SELECT g FROM Genre g LEFT JOIN g.tracks t GROUP BY g.id ORDER BY COUNT(t) DESC")
    List<Genre> findPopularGenres();
    
    // Busca gêneros por década de origem
    List<Genre> findByDecadeOrigin(String decade);
    
    // Busca gêneros com descrição contendo texto
    List<Genre> findByDescriptionContainingIgnoreCase(String keyword);
    
    // Verifica se existe pelo nome
    boolean existsByNameIgnoreCase(String name);

    List<Genre> findTop5ByOrderByTracksDesc();
}