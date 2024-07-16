package hello.springlv2se.dto;


import lombok.Getter;

@Getter
public class RequestBookDto {

    private String title;
    private String author;
    private String language;
    private String publisher;
    private String registration_date;
}
