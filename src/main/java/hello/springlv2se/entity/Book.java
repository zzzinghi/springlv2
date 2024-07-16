package hello.springlv2se.entity;

import hello.springlv2se.dto.RequestBookDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    @Column(name = "author", nullable = false, length = 255 )
    private String author;
    @Column(name = "language", nullable = false, length = 100)
    private String language;
    @Column(name = "publisher", nullable = false, length = 255)
    private String publisher;

    @Column(name = "date")
    private LocalDate registration_date;

    public Book(RequestBookDto requestBookDto) {
        this.title = requestBookDto.getTitle();
        this.author = requestBookDto.getAuthor();
        this.language = requestBookDto.getLanguage();
        this.publisher = requestBookDto.getPublisher();
        this.registration_date = LocalDate.now();
    }
}
