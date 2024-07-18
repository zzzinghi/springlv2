package hello.springlv2se.service;

import hello.springlv2se.dto.RequestMemberDto;
import hello.springlv2se.dto.ResponseMemberDto;
import hello.springlv2se.entity.Member;
import hello.springlv2se.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 데이터를 저장하는 메서드
    public ResponseMemberDto addMember(RequestMemberDto requestMemberDto) {
        Member member = new Member(requestMemberDto);
        Member saveMember = memberRepository.save(member);
        return new ResponseMemberDto(saveMember);
    }

    //선택한 회원 데이터 조회
    public ResponseMemberDto getMember(int id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 도서입니다."));
        ResponseMemberDto responseMemberDto = new ResponseMemberDto(member);
        return responseMemberDto;
    }

}
