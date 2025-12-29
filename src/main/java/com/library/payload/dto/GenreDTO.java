package com.library.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreDTO {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer displayOrder;
    private Boolean active;
    private Long parentGenreId;
    private String parentGenreName;
    private List<GenreDTO> subGenres;
    private Long bookCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
