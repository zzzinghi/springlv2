package com.sparta.trybook.bookservice;

import com.sparta.trybook.dto.BookEditDto;
import com.sparta.trybook.dto.BookEditResponseDto;
import com.sparta.trybook.dto.BookReadResponseDto;
import com.sparta.trybook.entity.BookRepository;
import org.springframework.stereotype.Service;

import com.sparta.trybook.dto.BookCreateDto;
import com.sparta.trybook.entity.TryBook;
import com.sparta.trybook.entity.BookRepository;

import java.awt.print.Book;
import java.util.NoSuchElementException;

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

    public BookReadResponseDto read(Integer bookId) throws NoSuchElementException {
        TryBook book = this.bookRepository.findById(bookId).orElseThrow();
        BookReadResponseDto bookReadResponseDto = new BookReadResponseDto();
        bookReadResponseDto.fromBook(book);
        return bookReadResponseDto;
    }
    //read 메소드와 달라진 점은 BookReadResponseDTO 를 반환하는 대신 새로 만든 BookEditResponseDTO를 반환한다는 점
    public BookEditResponseDto edit(Integer bookId) throws NoSuchElementException {
        TryBook book = this.bookRepository.findById(bookId).orElseThrow();
        return BookEditResponseDto.BookFactory(book);
    }
    public void update(BookEditDto bookEditDto) throws NoSuchElementException {
        TryBook book = this.bookRepository.findById(bookEditDto.getBookId()).orElseThrow();
        book = bookEditDto.fill(book);
        this.bookRepository.save(book);
    }
    //책 정보를 삭제하는 기능
    public void delete(Integer bookId) throws NoSuchElementException {
        TryBook book = this.bookRepository.findById(bookId).orElseThrow();
        this.bookRepository.delete(book);
    }


}

