package hello.springlv2se.dto;

import hello.springlv2se.entity.Loan;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ResponseLoanDto {
    private Long id;
    private Long bookId;
    private Long memberId;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private String status;

    // Loan 엔티티를 받아서 ResponseLoanDto로 변환하는 생성자
    public ResponseLoanDto(Loan loan) {
        this.id = loan.getId();
        this.bookId = loan.getBookId();
        this.memberId = loan.getMemberId();
        this.loanDate = LocalDate.parse(String.valueOf(loan.getLoanDate()));
        this.returnDate = LocalDate.parse(loan.getReturnDate() != null ? String.valueOf(loan.getReturnDate()) : "N/A");
        this.status = loan.getStatus();
    }
}
}
