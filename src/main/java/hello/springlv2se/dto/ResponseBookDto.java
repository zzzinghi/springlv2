package hello.springlv2se.dto;

import hello.springlv2se.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseBookDto {

    private Long id;
    private String title;
    private String author;
    private String language;
    private String publisher;
    private String registrationDate;
    private String formattedRegistrationDate;

    public ResponseBookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.language = book.getLanguage();
        this.publisher = book.getPublisher();
        this.registrationDate = String.valueOf(book.getRegistrationDate());
    }
}
