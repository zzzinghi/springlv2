package hello.springlv2se.service;

import hello.springlv2se.dto.RequestLoanDto;
import hello.springlv2se.dto.ResponseLoanDto;
import hello.springlv2se.entity.Loan;
import hello.springlv2se.repository.BookRepository;
import hello.springlv2se.repository.LoanRepository;
import hello.springlv2se.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    //1.도서 대출 메서드
    public ResponseLoanDto loanBook(RequestLoanDto requestLoanDto) {
        // 도서가 이미 대출 중인지 확인
        if (loanRepository.findById(requestLoanDto.getBookId()).isPresent()) {
            throw new RuntimeException("도서가 이미 대출 중입니다.");
        }
        // 회원이 반납하지 않은 책이 있는지 확인
        List<Loan> memberLoans = loanRepository.findAllByMemberIdOrderByLoanDateAsc(requestLoanDto.getMemberId());
        for (Loan loan : memberLoans) {
            if ("대출 중".equals(loan.getStatus())) {
                throw new RuntimeException("반납하지 않은 책이 있습니다.");
            }
        }
        // 대출 가능한 경우 대출 처리
        Loan loan = new Loan(requestLoanDto.getBookId(), requestLoanDto.getMemberId(), LocalDate.now(), "대출 중");
        Loan savedLoan = loanRepository.save(loan);
        return new ResponseLoanDto(savedLoan);
    }

    //2.도서 반납 메서드
    public void returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("대출 기록을 찾을 수 없습니다."));
        loan.returnBook(LocalDate.now());
        loanRepository.save(loan);
    }

    //3.회원 대출 내역 조회 메서드
    public List<ResponseLoanDto> getMemberLoans(Long memberId) {
        List<Loan> loans = loanRepository.findAllByMemberIdOrderByLoanDateAsc(memberId);
        return loans.stream().map(ResponseLoanDto::new).collect(Collectors.toList());
    }
}
