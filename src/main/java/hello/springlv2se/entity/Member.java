package hello.springlv2se.entity;

import hello.springlv2se.dto.RequestBookDto;
import hello.springlv2se.dto.RequestMemberDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    @Column(name = "uniquenumber", nullable = false, unique = true, length = 100)
    private String uniquenumber;

    @Column(name = "phonenumber", nullable = false, unique = true, length = 100)
    private String phonenumber;

    @Column(name = "address", nullable = false, length = 500)
    private String address;


    public Member(RequestMemberDto requestMemberDto) {
        this.name = requestMemberDto.getName();
        this.gender = requestMemberDto.getGender();
        this.uniquenumber = requestMemberDto.getUniquenumber();
        this.phonenumber = requestMemberDto.getPhonenumber();
        this.address = requestMemberDto.getAddress();
    }
}

