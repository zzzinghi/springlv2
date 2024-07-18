package hello.springlv2se.dto;

import hello.springlv2se.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseMemberDto {

    private int id;
    private String name;
    private String gender;
    private String phonenumber;
    private String address;

    public ResponseMemberDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.gender = member.getGender();
        this.phonenumber = member.getPhonenumber();
        this.address = member.getAddress();
    }
}

