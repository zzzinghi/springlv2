package com.sparta.trybook.bookservice;

import com.sparta.trybook.entity.BookRepository;
import org.springframework.stereotype.Service;

import com.sparta.trybook.dto.BookCreateDto;
import com.sparta.trybook.entity.TryBook;
import com.sparta.trybook.entity.BookRepository;

import java.awt.print.Book;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Integer insert(BookCreateDto bookCreateDto) {
        TryBook book = TryBook.builder()
                .title(bookCreateDto.getTitle())
                .price(bookCreateDto.getPrice())
                .build();

        this.bookRepository.save(book);
        return book.getBookId();
    }
}

