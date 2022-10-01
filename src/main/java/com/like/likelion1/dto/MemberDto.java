package com.like.likelion1.dto;

import com.like.likelion1.domain.Member;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {
    private Long id;
    private String name;
    private Long age;
    private String major;
    private String info;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedTime;

    public Member toEntity(){
        Member build = Member.builder()
                .id(id)
                .name(name)
                .age(age)
                .major(major)
                .info(info)
                .build();
        return build;
    }

    @Builder
    public MemberDto(Long id, String name, Long age, String major, String info, LocalDateTime createdTime, LocalDateTime modifiedTime){
        this.id = id;
        this.name = name;
        this.age = age;
        this.major = major;
        this.info = info;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }
}
