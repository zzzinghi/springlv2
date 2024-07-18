package hello.springlv2se.entity;

import hello.springlv2se.dto.RequestBookDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "books")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @Column(name = "language", nullable = false, length = 100)
    private String language;

    @Column(name = "publisher", nullable = false, length = 100)
    private String publisher;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    public Book(RequestBookDto requestBookDto) {
        this.title = requestBookDto.getTitle();
        this.author = requestBookDto.getAuthor();
        this.language = requestBookDto.getLanguage();
        this.publisher = requestBookDto.getPublisher();
        this.registrationDate = requestBookDto.getRegistrationDate() != null ? requestBookDto.getRegistrationDate() : LocalDate.now();
    }
}
