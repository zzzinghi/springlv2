package hello.springlv2se.controller;

import hello.springlv2se.dto.RequestMemberDto;
import hello.springlv2se.dto.ResponseMemberDto;
import hello.springlv2se.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    //회원 조회
    @GetMapping("/member/{id}")
    public ResponseEntity<ResponseMemberDto> getMemberById(@PathVariable int id) {
        ResponseMemberDto responseMemberDto = memberService.getMember(id);
        return new ResponseEntity<>(responseMemberDto, HttpStatus.OK);
    }

}
