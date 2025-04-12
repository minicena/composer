package br.com.composer.composer.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "artist")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Integer id;

    @Column(nullable = false, length = 40, name = "stage_name")
    private String stageName;

    @Column(nullable = false, length = 40, name = "artistic_name")
    private String artisticName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(length = 30, name = "artist_type")
    private String type; 
}