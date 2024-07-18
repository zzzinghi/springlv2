package hello.springlv2se.controller;

import hello.springlv2se.dto.RequestBookDto;
import hello.springlv2se.dto.ResponseBookDto;
import hello.springlv2se.service.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestBookController {
    private final BookService bookService;
    public static final Logger logger = LoggerFactory.getLogger("RESTCONTOLLER : "); // 로그 설정

    // 1. 도서 등록 : RequestDto객체를 받아서 새로운 book을 등록
    @PostMapping("/book")
    public ResponseEntity<ResponseBookDto> registerBook(@RequestBody RequestBookDto requestBookDto) {                   //requestBookDto 객체를 BookService의 addBook 메서드에 전달
        ResponseBookDto responseBookDto = bookService.addBook(requestBookDto);
        return new ResponseEntity<>(responseBookDto, HttpStatus.OK); //200
    }

    // 2. 도서 정보 조회 : 책 상세 페이지
    @GetMapping("/book/{id}")
    public ResponseEntity<ResponseBookDto> getBook(@PathVariable Long id) {
        ResponseBookDto responseBookDto = bookService.get(id);
        return new ResponseEntity<>(responseBookDto, HttpStatus.OK); //200
    }

    // 3.도서 전체 조회
    @GetMapping("/book") //
    public ResponseEntity<List<ResponseBookDto>> getBooks() { //title,author,등 담은 dto 받을수있음4
        ResponseEntity<List<ResponseBookDto>> entity = bookService.getBooks();
        return entity;
        //http응답을 생성하는 방법 중 하나, 클라이언트에게 http 200 ok 상태 코드를 반환하면서 응답 본문에 responseBookDto 객체를 포함하는 것을 의미
    }
    // 4. 도서 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();  //본문 생략하고, 클라이언트에게 요청의 성공 여부만을 전달함
    }

}
