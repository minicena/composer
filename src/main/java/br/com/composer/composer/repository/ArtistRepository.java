package br.com.composer.composer.repository;

import br.com.composer.composer.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    
    // Busca por nome artístico (contendo)
    List<Artist> findByArtisticNameContainingIgnoreCase(String artisticName);
    
    // Busca por tipo de artista
    List<Artist> findByTypeIgnoreCase(String type);
    
    // Busca combinada por nome e tipo
    List<Artist> findByArtisticNameContainingIgnoreCaseAndType(String artisticName, String type);
    
    // Busca personalizada com JPQL
    @Query("SELECT a FROM Artist a WHERE " +
           "LOWER(a.stageName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(a.artisticName) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Artist> searchArtists(@Param("query") String query);
    
    // Contagem por tipo de artista
    Long countByType(String type);

    List<Artist> findByType(String type);
}
