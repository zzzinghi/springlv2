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
}
