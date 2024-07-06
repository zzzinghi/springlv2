package com.sparta.trybook.bookcontroller;

import com.sparta.trybook.bookservice.BookService;
import com.sparta.trybook.dto.BookCreateDto;
import com.sparta.trybook.dto.BookEditDto;
import com.sparta.trybook.dto.BookEditResponseDto;
import com.sparta.trybook.dto.BookReadResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    @GetMapping("/book/read/{bookId}")
    public ModelAndView read(@PathVariable Integer bookId) {
        ModelAndView mav = new ModelAndView();

        try {
            BookReadResponseDto bookReadResponseDto = this.bookService.read(bookId);
            mav.addObject("bookReadResponseDto", bookReadResponseDto);
            mav.addObject("bookId", bookId);

            mav.setViewName("read");        // *** -> ("book/read")책 수정하기 할때 저장누르면 read 오류 페이지로 감..

        }catch(NoSuchElementException ex) {
            mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
            mav.addObject("message", "책 정보가 없습니다.");
            mav.addObject("location", "/book");
            mav.setViewName("common/error/422");
        }
        return mav;
        }
    //책 수정 화면 컴트롤러는 "읽기"와 동일하고, 반환하는 뷰와 dto만 다름,복사해도 되지만, @ExceptionHandler 사용해보자..?
            @GetMapping("/book/edit/{bookId}")
            public ModelAndView edit(@PathVariable Integer bookId) throws NoSuchElementException {
                ModelAndView mav = new ModelAndView();
                BookEditResponseDto bookEditResponseDto = this.bookService.edit(bookId);
            mav.addObject("bookEditResponseDto", bookEditResponseDto);
            mav.setViewName("edit");  //book/edit *** -> "edit"으로 고치니깐 됨
            return mav;
        }

//    @ExceptionHandler(NoSuchElementException.class)
//        public ModelAndView noSuchElementExceptionHandler(NoSuchElementException ex) {
//            ModelAndView mav = new ModelAndView();
//            mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
//            mav.addObject("message", "책 정보가 없습니다.");
//            mav.addObject("location", "/book/list");
//            mav.setViewName("common/error/422");
//            return mav;
//    }
//
    @ExceptionHandler(NoSuchElementException.class)
            public ModelAndView noSuchElementExceptionHandler(NoSuchElementException ex) {
              return this.error422("책 정보가 없습니다.", "/book/list");
    }

    private ModelAndView error422(String message, String location) {
        ModelAndView mav = new ModelAndView();
        mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        mav.addObject("message", message);
        mav.addObject("location", location);
        mav.setViewName("common/error/422");
        return mav;
    }

    //수정 요청을 처리할 메서드
    @PostMapping("/book/edit/{bookId}") // <- 저 경로로 들어오는 POST 요청을 처리함. {bookId}는 URL 경로 변수로, 특정 책의 ID를 나타냄
    public ModelAndView update(
            @Validated BookEditDto bookEditDto, // -> BookEditDto 객체를 유효성 검사함
            Errors errors) {    // -> 유효성 검사에서 발생한 오류를 담는 객체
        // 1. 유효성 검사에서 오류가 있는지 확인
        if (errors.hasErrors()) {   //-> 유효성 검사에서 오류가 발생했는지 확인
            // 2. 유효성 검사에서 발생한 오류 메시지를 문자열로 조합합니다
            String errorMessage =
                    errors  //유효성 검사에서 발생한 필드 오류 목록을 스트림으로 처리, 각 필드 이름과 오류 메시지를 조합한 문자열을 생성
                            .getFieldErrors()
                            .stream()
                            .map(x -> x.getField() + " : " + x.getDefaultMessage())
                            .collect(Collectors.joining("\n"))
                    ;
            // 3. 오류가 있을 경우 error422 메서드를 호출하여 에러 페이지를 반환합니다
            return this.error422(
                    errorMessage,
                    String.format("/book/edit/%s", bookEditDto.getBookId())
            );
        }
        // 4. 오류가 없을 경우 책 정보를 업데이트합니다.
        this.bookService.update(bookEditDto);

        // 5. 업데이트 후 책 상세 페이지로 리디렉션 합니다
        ModelAndView mav = new ModelAndView();
        mav.setViewName(String.format("redirect:/book/read/%s", bookEditDto.getBookId()));
        return mav;
    }

}
