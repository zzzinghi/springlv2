package hello.springlv2se.dto;


import lombok.Getter;

@Getter
public class RequestBookDto {

    private String title;
    private String author;
    private String language;
    private String publisher;
    private LocalDate registrationDate;

    public RequestBookDto(String title, String author, String language, String publisher, LocalDate registrationDate) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.publisher = publisher;
        this.registrationDate = registrationDate;
    }
}
