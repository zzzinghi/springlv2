package hello.springlv2se.dto;


import lombok.Getter;

@Getter  // getter 메서드를 자동으로 생성
public class RequestLoanDto {
    private Long bookId;
    private Long memberId;

    // 생성자
    public RequestLoanDto(Long bookId, Long memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
    }
}
//RequestLoanDto와 ResponseLoanDto는 서로 다른 목적을 가지고 있기 때문에 필드와 메서드의 구성에서 차이가 있습니다.
//RequestLoanDto는 클라이언트가 서버에 도서 대출 요청을 보낼 때 사용되는 데이터 전송 객체
//1.필수 데이터만 포함, 2.간결함,
// 3.서버 측 로직 분리: 서버는 이 데이터를 받아 필요한 추가 작업(예: 대출 날짜 설정, 상태 설정 등)을 처리합니다. 클라이언트는 단순히 필요한 ID 값만을 제공하면 됩니다.