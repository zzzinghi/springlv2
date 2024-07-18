package hello.springlv2se.controller;

import hello.springlv2se.dto.RequestLoanDto;
import hello.springlv2se.dto.ResponseLoanDto;
import hello.springlv2se.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // RESTful 웹 서비스의 컨트롤러임을 나타냄
@RequiredArgsConstructor  // final 필드를 포함하는 생성자 자동 생성
@RequestMapping("/api")  // 기본 요청 경로 지정
public class RestLoanController {
    private final LoanService loanService;

    // 도서 대출 엔드포인트
    @PostMapping("/loan")
    public ResponseEntity<ResponseLoanDto> loanBook(@RequestBody RequestLoanDto requestLoanDto) {
        ResponseLoanDto responseLoanDto = loanService.loanBook(requestLoanDto);
        return new ResponseEntity<>(responseLoanDto, HttpStatus.CREATED);  // 201 Created 상태 코드 반환
    }

    // 도서 반납 엔드포인트
    @PostMapping("/loan/return/{id}")
    public ResponseEntity<Void> returnBook(@PathVariable Long id) {
        loanService.returnBook(id);
        return ResponseEntity.noContent().build();  // 204 No Content 상태 코드 반환
    }

    // 회원 대출 내역 조회 엔드포인트
    @GetMapping("/loans/member/{memberId}")
    public ResponseEntity<List<ResponseLoanDto>> getMemberLoans(@PathVariable Long memberId) {
        List<ResponseLoanDto> responseLoanDtos = loanService.getMemberLoans(memberId);
        return new ResponseEntity<>(responseLoanDtos, HttpStatus.OK);  // 200 OK 상태 코드 반환
    }
}
