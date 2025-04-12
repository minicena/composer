package br.com.composer.composer.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "track")
@PrimaryKeyJoinColumn(name = "artist_id")
@Setter
@Getter
@NoArgsConstructor
public class Track extends Artist {
    
    @Column(nullable = false, length = 60, name = "track_title")
    private String title;
    
    @Column(name = "duration_seconds")
    private Integer duration;
    
    @Column(name = "release_date")
    private LocalDate releaseDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
    
    @Column(name = "is_single")
    private Boolean isSingle;
    
    @Column(name = "track_number")
    private Integer trackNumber;

    @Column(name = "artist_id", insertable = false, updatable = false)
    private Integer artistId;
    
    public Track(String stageName, String artisticName, LocalDate birthDate, String type,
                String title, Integer duration, LocalDate releaseDate, Genre genre, 
                Boolean isSingle, Integer trackNumber) {
        super(trackNumber, stageName, artisticName, birthDate, type);
        this.title = title;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.isSingle = isSingle;
        this.trackNumber = trackNumber;
    }
}
