package hello.springlv2se.dto;

import lombok.Getter;

@Getter
public class RequestMemberDto {

    private String name;
    private String gender;
    private String uniquenumber;
    private String phonenumber;
    private String address;

    public RequestMemberDto(String name, String gender, String uniquenumber, String phonenumber, String address) {
        this.name = name;
        this.gender = gender;
        this.uniquenumber = uniquenumber;
        this.phonenumber = phonenumber;
        this.address = address;

    }
}