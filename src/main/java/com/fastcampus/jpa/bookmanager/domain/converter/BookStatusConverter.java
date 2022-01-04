package com.fastcampus.jpa.bookmanager.domain.converter;

import com.fastcampus.jpa.bookmanager.repository.dto.BookStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//autoApply가 선언되어있으면 Book에티티의 @Convert를 선언안해줘도 된다.
@Converter(autoApply = true)
public class BookStatusConverter implements AttributeConverter<BookStatus,Integer> {
    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? new BookStatus(dbData) : null;
    }
}
