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

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestBookController {
    private final BookService bookService;
    public static final Logger logger = LoggerFactory.getLogger("RESTCONTOLLER : "); // 로그 설정

    // 1. 도서 등록 : RequestDto객체를 받아서 새로운 book을 등록
    @PostMapping("/book")
    public ResponseEntity<ResponseBookDto> registerBook(@RequestBody RequestBookDto requestBookDto) {
        ResponseBookDto responseBookDto = bookService.addBook(requestBookDto);
        return new ResponseEntity<>(responseBookDto, HttpStatus.OK); //200
    }

    // 1. 삭제된 주석
    //    URI location = ServletUriComponentsBuilder
    //            .fromCurrentRequest()
    //            .path("/{id}")
    //            .buildAndExpand(responseBookDto.getId())
    //            .toUri()

    // 2. 도서 정보 조회 : 책 상세 페이지
    @GetMapping("/book/{id}")
    public ResponseEntity<ResponseBookDto> getBook(@PathVariable Long id) { //title,author,등 담은 dto 받을수있음
        ResponseBookDto responseBookDto = bookService.get(id);
        return new ResponseEntity<>(responseBookDto, HttpStatus.OK); //200
        //http응답을 생성하는 방법 중 하나, 클라이언트에게 http 200 ok 상태 코드를 반환하면서 응답 본문에 responseBookDto 객체를 포함하는 것을 의미
    }

    // 3.도서 전체 조회
    @GetMapping("/book") //
    public ResponseEntity<List<ResponseBookDto>> getBooks() { //title,author,등 담은 dto 받을수있음4
        logger.info("도서 목록 요청을 받았습니다");
        ResponseEntity<List<ResponseBookDto>> entity = bookService.getBooks();
        logger.info("ResponseEntity를 서비스에서 받았습니다.");
        logger.info("서비스에서 받은 ResponseEntity를 반환해줍니다.");
        return entity;
        //http응답을 생성하는 방법 중 하나, 클라이언트에게 http 200 ok 상태 코드를 반환하면서 응답 본문에 responseBookDto 객체를 포함하는 것을 의미
    }

    // 4. 도서 삭제
}
