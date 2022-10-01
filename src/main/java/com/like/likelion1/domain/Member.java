package com.like.likelion1.domain;

import com.like.likelion1.dto.MemberDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length= 100, nullable = false)
    private String name;

    @Column(nullable=false)
    private Long age;

    @Column(length=100, nullable = false)
    private String major;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String info;

    @Builder
    public Member(Long id,String name, Long age, String major, String info){
        this.id = id;
        this.name = name;
        this.age = age;
        this.major = major;
        this.info = info;
    }

    public void update(MemberDto memberDto) {
        this.name = memberDto.getName();
        this.age = memberDto.getAge();
        this.major = memberDto.getMajor();
        this.info = memberDto.getInfo();
    }
}
