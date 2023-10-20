package com.example.youandme.controller;

import com.example.youandme.entity.Board;
import com.example.youandme.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm() {
        return "boardwrite";
    }

    //Process : 처리, 매개변수로 entity패키지에 Data를 사용해서 board 바로 받을 수 있음.
    @PostMapping("/board/writepro")
    public String boardWritePro(Board board) {
        boardService.write(board);
        return "";
    }

    //게시글 목록 불러오기 (데이터를 담아서 사용하는 페이지로 보여주기 위해 Model 사용)
    @GetMapping("/board/list")
    public String boardList(Model model) {

        model.addAttribute("list", boardService.boardList()); //반환된 boardlist를 list란 이름으로 넘기겠다.

        return "board/list";
    }

}
