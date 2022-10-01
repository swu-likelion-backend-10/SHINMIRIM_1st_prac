package hello.hellospring.post.controller;

import hello.hellospring.post.dto.BoardDto;
import hello.hellospring.post.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    private  final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String list(Model model)
    {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("boardList", boardDtoList);
        return "board/list.html";
    }

    @GetMapping("/post")
    public String write(){
        return "board/write.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);

        model.addAttribute("boardDto", boardDto);
        return "board/detail.html";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto",boardDto);
        return "board/update.html";
    }

    @PutMapping("/post/edit/{no}")
    public String update(@PathVariable("no") Long id, BoardDto boardDto){
        boardService.updatePost(id, boardDto);
        return "redirect:/post/{no}";
    }

    @DeleteMapping("/post/delete/{no}")
    public String delete(@PathVariable("no") Long id){
        boardService.deletePost(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String list(@RequestParam("keyword") String keyword, Model model)
    {
        List<BoardDto> boardDtoList;
        if(keyword.equals("")){
            boardDtoList = boardService.getBoardList();
        }else{
            boardDtoList = boardService.searchBoardList(keyword);
        }

        model.addAttribute("boardList", boardDtoList);
        return "board/list.html";
    }
}
