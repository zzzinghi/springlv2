package hello.springlv2se.controller;

import hello.springlv2se.dto.RequestBookDto;
import hello.springlv2se.dto.ResponseBookDto;
import hello.springlv2se.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestBookController {
    private final BookService bookService;

    //RequestDto객체를 받아서 새로운 book을 등록
    @PostMapping
    public ResponseEntity<ResponseBookDto> registerBook(@ModelAttribute RequestBookDto requestBookDto) {
        ResponseBookDto responseBookDto = bookService.addBook(requestBookDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseBookDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(responseBookDto);
    }
    //책 '조회' 요청에 응답하는 메서드
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBookDto> getBook(@PathVariable Long id) { //title,author,등 담은 dto 받을수있음
        ResponseBookDto responseBookDto = bookService.get(id);

        return ResponseEntity.ok(responseBookDto);
        //http응답을 생성하는 방법 중 하나, 클라이언트에게 http 200 ok 상태 코드를 반환하면서 응답 본문에 responseBookDto 객체를 포함하는 것을 의미
    }

    //책 상세 페이지
    @GetMapping("/bookdetails/{id}")
    public String bookdetails(Model model, @ModelAttribute Long id) {
        ResponseBookDto responseBookDto = bookService.get(id);
        model.addAttribute("book", responseBookDto);
        return "bookdetails";
    }

}
