package com.sparta.trybook.dto;

import java.time.LocalDateTime;

import com.sparta.trybook.entity.TryBook;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookReadResponseDto {
    private Integer bookId;
    private String title;
    private Integer price;
    private LocalDateTime insertDateTime;

    public BookReadResponseDto fromBook(TryBook book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.insertDateTime = book.getInsertDateTime();
        return this;
    }

    public static BookReadResponseDto BookFactory(TryBook book) {
        BookReadResponseDto bookReadResponseDTO = new BookReadResponseDto();

        bookReadResponseDTO.fromBook(book);
        return bookReadResponseDTO;
    }
}
