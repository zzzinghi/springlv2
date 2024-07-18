package hello.springlv2se.controller;

import hello.springlv2se.dto.RequestMemberDto;
import hello.springlv2se.dto.ResponseMemberDto;
import hello.springlv2se.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestMemberController {

    private final MemberService memberService;

    //회원 등록
    @PostMapping("/member")
    public ResponseEntity<ResponseMemberDto> registerMember(@RequestBody RequestMemberDto requestMemberDto) {
        ResponseMemberDto responseMemberDto = memberService.addMember(requestMemberDto);
        return new ResponseEntity<>(responseMemberDto, HttpStatus.OK);
    }
}
