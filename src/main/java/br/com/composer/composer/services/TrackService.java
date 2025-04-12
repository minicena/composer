package br.com.composer.composer.services;

import br.com.composer.composer.entity.Track;
import br.com.composer.composer.repository.TrackRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrackService {

    private final TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Transactional(readOnly = true)
    public List<Track> findAll() {
        return trackRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Track findById(Integer id) {
        return trackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Track not found with id: " + id));
    }

    @Transactional
    public Track save(Track track) {
        return trackRepository.save(track);
    }

    @Transactional
    public Track update(Integer id, Track trackDetails) {
        Track track = findById(id);
        track.setTitle(trackDetails.getTitle());
        track.setDuration(trackDetails.getDuration());
        track.setReleaseDate(trackDetails.getReleaseDate());
        track.setGenre(trackDetails.getGenre());
        track.setIsSingle(trackDetails.getIsSingle());
        track.setTrackNumber(trackDetails.getTrackNumber());
        return trackRepository.save(track);
    }

    @Transactional
    public void delete(Integer id) {
        Track track = findById(id);
        trackRepository.delete(track);
    }

    @Transactional(readOnly = true)
    public List<Track> findByGenreId(Integer genreId) {
        return trackRepository.findByGenreId(genreId);
    }

    @Transactional(readOnly = true)
    public List<Track> findByArtistId(Integer artistId) {
        return trackRepository.findByArtistId(artistId);
    }
}