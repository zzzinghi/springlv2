package com.sparta.trybook.bookcontroller;

import com.sparta.trybook.bookservice.BookService;
import com.sparta.trybook.dto.BookCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller         //브라우저의 요청을 받아들이는 컨트롤러라고 인지해서 자바 빈으로 등록해서 관리하게 함, 프레임워크에서 관리하는 클래스가 됨
public class BookController {

    //컴트롤러에서 서비스 클래스를 사용하기 위해서 의존성 주입?
    //필드 주입
    @Autowired          // 컨트롤러에서 @Autowired 의해서 BookService를 주입받고 있음.
    private BookService bookService;

    @GetMapping("/book/create") //브라우저 주소가 /creqte일 때 실행되는 자바 컨트롤러 메서드 생성
    public String create() {       //create() 메소드는 브라우저에서 /book/create주소가 http GET 방식으로 입력되었을 때 book/create 경로의 뷰를 보여주는 컨트롤러 메서드
        return "/create";       //책 생성 컨트롤러에서 book/create  경로를 반환했으므로, 실제 화면을 담당하는 뷰 코드를 작성해야함
        // "/book/create" 이렇게 돼있으니깐 창이 아예 안뜸.
    }

    @PostMapping("/book/create")        //데이터 생성시, 데이터 변경에 필요한 액션(생성,수정,삭제)할 때 사용
    public String insert(BookCreateDto bookCreateDto) {
        Integer bookId = this.bookService.insert(bookCreateDto);
        return String.format("redirect:/book/read/%s", bookId);
    }
}
