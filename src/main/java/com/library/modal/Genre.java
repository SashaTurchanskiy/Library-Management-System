package com.library.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Genre name is required")
    private String code;

    @NotBlank(message = "Genre name is required")
    private String name;

    @Size(max = 500, message = "Description can be at most 500 characters")
    private String description;

    @Min(value = 0, message = "Display order must be non-negative")
    private Integer displayOrder = 0;

    @Column(nullable = false)
    private Boolean active = true;

    @ManyToOne
    private Genre parentGenre;

    @OneToMany
    private List<Genre> subGenres = new ArrayList<>();



}
