package hello.springlv2se.repository;

import hello.springlv2se.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    // 회원 ID로 대출 내역을 조회하고 대출일 기준으로 오름차순 정렬하는 메서드
    List<Loan> findAllByMemberIdOrderByLoanDateAsc(Long memberId);
}
