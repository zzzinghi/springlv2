package com.sparta.trybook.entity;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;

//6.3책 리포지터리 인터페이스 만들기
public interface BookRepository extends JpaRepository<TryBook, Integer> {
    public List<TryBook> findByTitleContains(String title, Pageable pageable);  //Pageable -> 도메인 임포트함. 맞는지 모르겠음
}

