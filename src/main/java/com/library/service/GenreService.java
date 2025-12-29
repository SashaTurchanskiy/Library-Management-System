package com.library.service;

import com.library.exception.GenreException;
import com.library.modal.Genre;
import com.library.payload.dto.GenreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreService {
    GenreDTO createGenre(GenreDTO genreDTO);
    List<GenreDTO>  getAllGenres();
    GenreDTO getGenreById(Long genreId) throws Exception;
    GenreDTO updateGenre(Long genreId, GenreDTO genreDTO) throws GenreException;
    void deleteGenre(Long genreId) throws GenreException;
    void hardDeleteGenre(Long genreId) throws GenreException;
    List<GenreDTO> getAllActiveGenresWithSubGenres();
    List<GenreDTO> getTopLevelGenres();
    //Page<GenreDTO> searchGenres(String searchTerm, Pageable pageable);
    long getTotalActiveGenres();
    long getBookCountByGenreId(Long genreId);


}
