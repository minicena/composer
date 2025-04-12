package br.com.composer.composer.controller;

import br.com.composer.composer.entity.Track;
import br.com.composer.composer.services.TrackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public ResponseEntity<List<Track>> getAllTracks() {
        List<Track> tracks = trackService.findAll();
        return ResponseEntity.ok(tracks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> getTrackById(@PathVariable Integer id) {
        Track track = trackService.findById(id);
        return ResponseEntity.ok(track);
    }

    @PostMapping
    public ResponseEntity<Track> createTrack(@RequestBody Track track) {
        Track savedTrack = trackService.save(track);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTrack.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedTrack);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Track> updateTrack(@PathVariable Integer id, @RequestBody Track track) {
        Track updatedTrack = trackService.update(id, track);
        return ResponseEntity.ok(updatedTrack);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrack(@PathVariable Integer id) {
        trackService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-genre/{genreId}")
    public ResponseEntity<List<Track>> getTracksByGenre(@PathVariable Integer genreId) {
        List<Track> tracks = trackService.findByGenreId(genreId);
        return ResponseEntity.ok(tracks);
    }

    @GetMapping("/by-artist/{artistId}")
    public ResponseEntity<List<Track>> getTracksByArtist(@PathVariable Integer artistId) {
        List<Track> tracks = trackService.findByArtistId(artistId);
        return ResponseEntity.ok(tracks);
    }
}