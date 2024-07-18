package hello.springlv2se.service;

import hello.springlv2se.dto.RequestBookDto;
import hello.springlv2se.dto.ResponseBookDto;
import hello.springlv2se.entity.Book;
import hello.springlv2se.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    //1.데이터를 저장하는 메서드
    //addBook 메서드는 requestDto 객체를 입력 받고, 새로운 book을 db에 저장하고, 저장된 책의 id를 포함한  responseDto 객체를 반환함
    public ResponseBookDto addBook(RequestBookDto requestBookDto) {
        Book book = new Book(requestBookDto);
        Book savaBook = bookRepository.save(book);
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
    //도서 전체 조회
    public ResponseEntity<List<ResponseBookDto>> getBooks(){
        List<Book> books = bookRepository.findAllByOrderByRegistrationDateAsc(); // 도서 데이터 받아오는 단계
        List<ResponseBookDto> responseBookDtoList = books.stream().map(ResponseBookDto::new).toList(); // ResponseBookDto로 포장하는 단계
        ResponseEntity<List<ResponseBookDto>> entity = new ResponseEntity<>(responseBookDtoList, HttpStatus.OK); // ResponseEntity로 포장하는 단계
        return entity;
    }

    //도서 삭제
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 도서입니다."));
        bookRepository.delete(book);
    }
}
