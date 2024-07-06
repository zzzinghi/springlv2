package com.sparta.trybook.dto;

import com.sparta.trybook.entity.TryBook;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BookEditResponseDto {
    private Integer bookId;
    private String title;
    private Integer price;
    private LocalDateTime insertDateTime;

    public BookEditResponseDto fromBook(TryBook book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.insertDateTime = book.getInsertDateTime();
        return this;
    }

    public static BookEditResponseDto BookFactory(TryBook book) {
        BookEditResponseDto bookEditResponseDtO = new BookEditResponseDto();

        bookEditResponseDtO.fromBook(book);
        return bookEditResponseDtO;
    }
}
