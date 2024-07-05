package com.sparta.trybook.entity;


import org.springframework.data.jpa.repository.JpaRepository;

//6.3책 리포지터리 인터페이스 만들기
public interface BookRepository extends JpaRepository<TryBook, Integer> {

}
