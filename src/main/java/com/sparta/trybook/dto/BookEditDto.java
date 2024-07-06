package com.sparta.trybook.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import com.sparta.trybook.entity.TryBook;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.awt.print.Book;

@NoArgsConstructor          //맘대로 추가
@Getter
@Setter
public class BookEditDto {
    @NonNull
    @Positive
    private Integer bookId;

    @NonNull
    @NotBlank
    private String title;

    @NonNull
    @Min(1000)
    private Integer price;

    public TryBook fill(TryBook book) {
        book.setTitle(this.title);
        book.setPrice(this.price);
        return book;
    }
}
