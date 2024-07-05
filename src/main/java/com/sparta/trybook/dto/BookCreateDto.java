package com.sparta.trybook.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookCreateDto {    //값을 담는 컨테이너 객체, title,price 입력했을때 클라이언트가 서버로 전달한 값들을 담는 역할
                                //라이언트의 HTTP 파라미터를 담는 컨테이너 객체로 사용됩니다.
    @NonNull
    private String title;

    @NonNull
    private Integer price;
}

