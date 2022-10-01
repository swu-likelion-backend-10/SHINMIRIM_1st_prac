package com.like.likelion1.controller;

import com.like.likelion1.dto.MemberDto;
import com.like.likelion1.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String list(Model model){
        List<MemberDto> memberDtoList = memberService.getBoardList();
        model.addAttribute("memberList", memberDtoList);
        return "list.html";
    }

    @GetMapping("/info")
    public String info(){
        return "info.html";
    }

    @PostMapping("/writeInfo")
    public String writeInfo(MemberDto memberDto){
        Long id = memberService.writeInfo(memberDto);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        MemberDto memberDto = memberService.getPost(id);

        model.addAttribute("memberDto", memberDto);
        return "detail.html";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        MemberDto memberDto = memberService.getPost(id);
        model.addAttribute("memberDto",memberDto);
        return "update.html";
    }

    @PutMapping("/post/edit/{no}")
    public String update(@PathVariable("no") Long id, MemberDto memberDto){
        memberService.updatePost(id, memberDto);
        return "redirect:/post/{no}";
    }

    @DeleteMapping("/post/delete/{no}")
    public String delete(@PathVariable("no") Long id){
        memberService.deletePost(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String list(@RequestParam("keyword") String keyword, Model model)
    {
        List<MemberDto> memberDtoList;
        if(keyword.equals("")){
            memberDtoList = memberService.getBoardList();
        }else{
            memberDtoList = memberService.searchBoardList(keyword);
        }

        model.addAttribute("memberList", memberDtoList);
        return "list.html";
    }
}
