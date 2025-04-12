package br.com.composer.composer.services;
import br.com.composer.composer.entity.Artist;
import br.com.composer.composer.repository.ArtistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Transactional(readOnly = true)
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Artist findById(Integer id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artist not found with id: " + id));
    }

    @Transactional
    public Artist save(Artist artist) {
        return artistRepository.save(artist);
    }

    @Transactional
    public Artist update(Integer id, Artist artistDetails) {
        Artist artist = findById(id);
        artist.setStageName(artistDetails.getStageName());
        artist.setArtisticName(artistDetails.getArtisticName());
        artist.setBirthDate(artistDetails.getBirthDate());
        artist.setType(artistDetails.getType());
        return artistRepository.save(artist);
    }

    @Transactional
    public void delete(Integer id) {
        Artist artist = findById(id);
        artistRepository.delete(artist);
    }

    @Transactional(readOnly = true)
    public List<Artist> search(String name, String type) {
        if (name != null && type != null) {
            return artistRepository.findByArtisticNameContainingIgnoreCaseAndType(name, type);
        } else if (name != null) {
            return artistRepository.findByArtisticNameContainingIgnoreCase(name);
        } else if (type != null) {
            return artistRepository.findByType(type);
        }
        return artistRepository.findAll();
    }
}
