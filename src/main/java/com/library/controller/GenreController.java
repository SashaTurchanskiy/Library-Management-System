package com.library.controller;

import com.library.exception.GenreException;
import com.library.modal.Genre;
import com.library.payload.dto.GenreDTO;
import com.library.payload.response.ApiResponse;
import com.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/create")
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO){
        GenreDTO createdGenre = genreService.createGenre(genreDTO);
        return ResponseEntity.ok(createdGenre);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllGenres(){
        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getGenreById(@PathVariable Long id) throws Exception {
        GenreDTO genre = genreService.getGenreById(id);
        return ResponseEntity.ok(genre);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable Long id,
                                         @RequestBody GenreDTO genreDTO) throws GenreException {
        GenreDTO updatedGenre = genreService.updateGenre(id, genreDTO);
        return ResponseEntity.ok(updatedGenre);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long id) throws GenreException {
        genreService.deleteGenre(id);
        ApiResponse apiResponse = new ApiResponse("Genre deleted successfully", true);
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/hardDelete/{id}")
    public ResponseEntity<?> hardDeleteGenre(@PathVariable Long id) throws GenreException {
        genreService.hardDeleteGenre(id);
        ApiResponse apiResponse = new ApiResponse("Genre deleted successfully", true);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/topLevel")
    public ResponseEntity<?> getTopLevelGenres(){
        List<GenreDTO> topLevelGenres = genreService.getTopLevelGenres();
        return ResponseEntity.ok(topLevelGenres);
    }
    @GetMapping("/count")
    public ResponseEntity<?> countActiveGenres(){
        long count = genreService.getTotalActiveGenres();
        return ResponseEntity.ok(count);
    }
    @GetMapping("/bookCount/{id}")
    public ResponseEntity<?> getBookCountGenres(@PathVariable Long id){
        Long count = genreService.getBookCountByGenreId(id);
        return ResponseEntity.ok(count);
    }
}
