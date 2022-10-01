package com.like.likelion1.service;

import com.like.likelion1.domain.Member;
import com.like.likelion1.dto.MemberDto;
import com.like.likelion1.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepo;

    public MemberService(MemberRepository memberRepo){
        this.memberRepo = memberRepo;
    }


    @Transactional
    public Long writeInfo(MemberDto memberDto) {
        return memberRepo.save(memberDto.toEntity()).getId();
    }

    @Transactional
    public List<MemberDto> getBoardList(){
        List<Member> members = memberRepo.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();

        for(Member member : members){
            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .age(member.getAge())
                    .major(member.getMajor())
                    .info(member.getInfo())
                    .createdTime(member.getCreatedTime())
                    .build();

            memberDtoList.add(memberDto);
        }

        return memberDtoList;
    }

    @Transactional
    public MemberDto getPost(Long id) {
        Optional<Member> memberWrapper = memberRepo.findById(id);
        Member member = memberWrapper.get();

        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .major(member.getMajor())
                .info(member.getInfo())
                .createdTime(member.getCreatedTime())
                .modifiedTime(member.getModifiedTime())
                .build();

        return memberDto;
    }

    @Transactional
    public Long updatePost(Long id, MemberDto memberDto) {
        Member member = memberRepo.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."+id));
        member.update(memberDto);

        return id;
    }


    @Transactional
    public void deletePost(Long id) {
        memberRepo.deleteById(id);
    }

    @Transactional
    public List<MemberDto> searchBoardList(String name) {
        List<Member> members = memberRepo.findByNameContaining(name);
        List<MemberDto> memberDtoList = new ArrayList<>();

        for(Member member : members){
            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .age(member.getAge())
                    .major(member.getMajor())
                    .info(member.getInfo())
                    .createdTime(member.getCreatedTime())
                    .build();

            memberDtoList.add(memberDto);
        }



        return memberDtoList;
    }
}
