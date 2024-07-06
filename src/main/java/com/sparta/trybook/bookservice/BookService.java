package com.sparta.trybook.bookservice;

import com.sparta.trybook.dto.*;
import com.sparta.trybook.entity.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sparta.trybook.entity.TryBook;
import com.sparta.trybook.entity.BookRepository;

import java.awt.print.Book;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public List<BookListResponseDto> bookList(String title, Integer page) {
        final int pageSize = 3;
        List<TryBook> books;

        if (page == null) {
            page = 0;
        } else {
            page -= 1;
        }
        if (title == null) {
            Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "insertDateTime");  //Direction -> 도메인 임포트함. 맞는지 모르겠음
            books = this.bookRepository.findAll(pageable).toList();
        }
        else {
            Pageable pageable = PageRequest.of(page, pageSize);
            Sort sort = Sort.by(Sort.Order.desc("insertDateTime")); //Order -> 임포트 order.in.Sort(of.arg.springframework.data.domain) 했는데 무슨뜻이지
            pageable.getSort().and(sort);
            books = this.bookRepository.findByTitleContains(title, pageable);
        }
        return books.stream().map(book ->
                new BookListResponseDto(book.getBookId(), book.getTitle())
        ).collect(Collectors.toList());

    }

}

