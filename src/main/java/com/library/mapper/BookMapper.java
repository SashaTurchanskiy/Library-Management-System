package com.library.mapper;

import com.library.modal.Book;
import com.library.payload.dto.BookDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDTO(Book book);

    Book toEntity(BookDTO bookDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "isbn", ignore = true)
    void updateEntityFromDTO(BookDTO bookDTO, @MappingTarget Book book);

}
