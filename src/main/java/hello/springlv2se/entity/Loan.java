package hello.springlv2se.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "loan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "status", nullable = false)
    private String status;  // 대출 상태 (대출 중, 반납 완료 등)

    //대출 기록을 생성하는 생성자
    public Loan(Long bookId, Long memberId, LocalDate loanDate, String status) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.status = status;
    }
    //책을 반납하는 메서드
    public void returnBook(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.status = "반납 완료";
    }
}
