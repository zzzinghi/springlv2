package hello.springlv2se.repository;


import hello.springlv2se.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderByRegistrationDateAsc();   //오름차순
}
