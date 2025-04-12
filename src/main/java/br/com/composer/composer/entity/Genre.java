package br.com.composer.composer.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "music_genre")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Integer id;
    
    @Column(nullable = false, length = 30, unique = true)
    private String name;
    
    @Column(length = 200)
    private String description;
    
    @Column(name = "decade_origin")
    private String decadeOrigin;
    
    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Track> tracks = new ArrayList<>();
}
