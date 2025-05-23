package br.com.composer.composer.repository;

import br.com.composer.composer.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Integer> {
    
    // Busca por ID do gênero
    List<Track> findByGenreId(Integer genreId);
    

    
    // Busca por faixas lançadas após uma data
    List<Track> findByReleaseDateAfter(java.time.LocalDate date);
    
    // Busca por faixas singles
    List<Track> findByIsSingleTrue();
    
    // Busca as faixas mais longas
    @Query("SELECT t FROM Track t ORDER BY t.duration DESC LIMIT 10")
    List<Track> findTop10LongestTracks();

    @Query("SELECT t FROM Track t WHERE t.artistId = :artistId")
    List<Track> findByArtistId(@Param("artistId") Integer artistId);
    
    // Contagem de faixas por gênero
    @Query("SELECT g.name, COUNT(t) FROM Track t JOIN t.genre g GROUP BY g.name")
    List<Object[]> countTracksByGenre();
}