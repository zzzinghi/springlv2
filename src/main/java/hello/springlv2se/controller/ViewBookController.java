package hello.springlv2se.controller;

import hello.springlv2se.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class ViewBookController {

    private final BookService bookService;

    //첫 페이지
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    //책 등록 페이지 이동
    @GetMapping("/books/new")
    public String newBook(Model model) {
        return "newBook";
    }

    //책 전체 목록 조회
    @GetMapping("/books")
    public String viewBooks(Model model) {
        //전체 도서 목록 조회
//        List<Book> books = bookService.getBook();       //조회된 목록을 모델에 추가, books.html 페이지를 반환
//        model.addAttribute("books", books);
        return "books";
    }
}
