package com.sparta.trybook.dto;

import lombok.Getter;

@Getter
public class BookListResponseDto {      //책 목록을 클라이언트에 응답하기 위한 Dto 객체
    private Integer bookId;
    private String title;

    public BookListResponseDto(Integer bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }
}
