package br.com.composer.composer.services;

import br.com.composer.composer.entity.Genre;
import br.com.composer.composer.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Genre findById(Integer id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + id));
    }

    @Transactional
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre update(Integer id, Genre genreDetails) {
        Genre genre = findById(id);
        genre.setName(genreDetails.getName());
        genre.setDescription(genreDetails.getDescription());
        genre.setDecadeOrigin(genreDetails.getDecadeOrigin());
        return genreRepository.save(genre);
    }

    @Transactional
    public void delete(Integer id) {
        Genre genre = findById(id);
        genreRepository.delete(genre);
    }

    @Transactional(readOnly = true)
    public List<Genre> findPopularGenres() {
        return genreRepository.findTop5ByOrderByTracksDesc();
    }
}