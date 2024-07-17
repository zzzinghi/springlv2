package hello.springlv2se.service;

import hello.springlv2se.dto.RequestBookDto;
import hello.springlv2se.dto.ResponseBookDto;
import hello.springlv2se.entity.Book;
import hello.springlv2se.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    //데이터를 저장하는 메서드
    //addBook 메서드는 requestDto 객체를 입력 받고, 새로운 book을 db에 저장하고, 저장된 책의 id를 포함한  responseDto 객체를 반환함
    public ResponseBookDto addBook(RequestBookDto requestBookDto) {
        Book book = new Book(requestBookDto);
//        Book book = Book.builder()
//                .title(requestBookDto.getTitle())
//                .author(requestBookDto.getAuthor())
//                .language(requestBookDto.getLanguage())
//                .publisher(requestBookDto.getPublisher())
//                .registrationDate(LocalDate.now())
//                .build();
        Book savaBook = bookRepository.save(book);


//        return ResponseBookDto.builder()
//                .id(savaBook.getId())
//                .author(savaBook.getAuthor())
//                .language(savaBook.getLanguage())
//                .publisher(savaBook.getPublisher())
//                .registration_date(String.valueOf(savaBook.getRegistrationDate()))
//                .build();
        return new ResponseBookDto(savaBook);
    }

    //선택한 도서 정보 조회 기능
    public ResponseBookDto get(Long id) {   //DB에서 특정한 id를 가진 데이터를 찾아오기 위해 id를 받음
        Book book = bookRepository.findById(id) //Repository의 findById 메서드 사용해서 book을 가져온다
                .orElseThrow(() -> new RuntimeException("존재하지 않는 도서입니다."));
        ResponseBookDto responseBookDto = new ResponseBookDto(book);
        if(book.getRegistrationDate() != null) {
            responseBookDto.setFormattedRegistrationDate(
                    book.getRegistrationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            );
        } else {
            responseBookDto.setFormattedRegistrationDate("N/A");
        }
        return responseBookDto;
}
//        return ResponseBookDto.builder()
//                .id(book.getId())
//                .title(book.getTitle())
//                .author(book.getAuthor())
//                .language(book.getLanguage())
//                .publisher(book.getPublisher())
//                .registration_date(String.valueOf(book.getRegistrationDate()))
//                .build();
//  }

    //도서 전체 목록 조회 -> 등록일 기준 오름차순으로 정렬??
    public List<Book> getBook() {
        return bookRepository.findAllByOrderByRegistrationDateAsc();
    }
}
