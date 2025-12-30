package com.library.mapper;

import com.library.modal.Genre;
import com.library.payload.dto.GenreDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


//@Component
//@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public interface GenreMapper {

//    private final GenreRepository genreRepository;
//
//    public GenreDTO toDTO(Genre savedGenre) {
//        if (savedGenre == null) {
//            return null;
//        }
//
//            GenreDTO dto = GenreDTO.builder()
//                    .id(savedGenre.getId())
//                    .code(savedGenre.getCode())
//                    .name(savedGenre.getName())
//                    .description(savedGenre.getDescription())
//                    .displayOrder(savedGenre.getDisplayOrder())
//                    .active(savedGenre.getActive())
//                    .createdAt(savedGenre.getCreatedAt())
//                    .updatedAt(savedGenre.getUpdatedAt())
//                    .build();
//
//            if (savedGenre.getParentGenre() != null) {
//                dto.setParentGenreId(savedGenre.getParentGenre().getId());
//                dto.setParentGenreName(savedGenre.getParentGenre().getName());
//            }
//            if (savedGenre.getSubGenres() != null && !savedGenre.getSubGenres().isEmpty()) {
//                dto.setSubGenres(savedGenre.getSubGenres().stream()
//                        .filter(Genre::getActive)
//                        .map(this::toDTO).collect(Collectors.toList()));
//            }
////
////                    dto.setBookCount((long)(savedGenre.getB);
//
//            return dto;
//        }
//
//        public Genre toEntity(GenreDTO genreDTO){
//            if (genreDTO == null){
//                return null;
//            }
//             Genre genre = Genre.builder()
//                    .code(genreDTO.getCode())
//                    .name(genreDTO.getName())
//                    .description(genreDTO.getDescription())
//                    .displayOrder(genreDTO.getDisplayOrder())
//                    .active(genreDTO.getActive())
//                    .build();
//            if (genreDTO.getParentGenreId() != null){
//                genreRepository.findById(genreDTO.getParentGenreId())
//                        .ifPresent(genre::setParentGenre);
//                //genre.setParentGenre(parentGenre);
//            }
//            return genre;
//        }
//
//        public void updateEntityFromDTO(GenreDTO genreDTO, Genre existingGenre){
//        if (genreDTO == null || existingGenre == null){
//            return;
//        }
//            existingGenre.setCode(genreDTO.getCode());
//            existingGenre.setName(genreDTO.getName());
//            existingGenre.setDescription(genreDTO.getDescription());
//            existingGenre.setDisplayOrder(genreDTO.getDisplayOrder() != null ? existingGenre.getDisplayOrder() : 0);
//        if (genreDTO.getActive() != null){
//            existingGenre.setActive(genreDTO.getActive());
//        }
//        if (genreDTO.getParentGenreId() != null){
//            genreRepository.findById(genreDTO.getParentGenreId())
//                    .ifPresent(existingGenre::setParentGenre);
//        }
//        }
//
//        public List<GenreDTO> toDTOList(List<Genre> genreList){
//            return genreList.stream()
//                    .map(this::toDTO)
//                    .collect(Collectors.toList());
//        }
    @Mapping(source = "parentGenre.id", target = "parentGenreId")
    @Mapping(source = "parentGenre.name", target = "parentGenreName")
    GenreDTO toDTO(Genre genre);

    Genre toEntity(GenreDTO genreDTO);

    List<GenreDTO> toDTOList(List<Genre> genreList);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(GenreDTO genreDTO, @MappingTarget Genre existingGenre);


}
