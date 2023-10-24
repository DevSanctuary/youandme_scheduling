package com.example.youandme.controller;

import com.example.youandme.entity.Board;
import com.example.youandme.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 게시글 등록 페이지
    @GetMapping("/register")
    public String showRegisterForm(Model model) {

        model.addAttribute("board", new Board());
        return "board/register";

    }

    // 게시글 등록
    @PostMapping("/register")
    public String register(Board board, Model model,
                           @RequestParam("file") MultipartFile file) {

        try {
            if (boardService.register(board, file)) {
                model.addAttribute("message", "글 작성이 완료되었습니다.");
            } else {
                model.addAttribute("message", "글 작성이 실패했습니다.");
            }

        } catch (IOException e) {
            model.addAttribute("message", "파일 업로드 중 오류가 발생했습니다.");
        }

        model.addAttribute("searchUrl", "/board/list");

        return "board/message";

    }

    // 게시글 목록 조회 페이지
    @GetMapping("/list")
    // 페이징 처리
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "bno", direction = Sort.Direction.DESC)
                            Pageable pageable, String searchKeyword) {

        Page<Board> list = null;

        //검색 처리
        if (searchKeyword == null) {
            list = boardService.boardList(pageable);                        //검색 단어가 없는 경우, 그대로 목록 반환
        } else {
            list = boardService.boardSearchList(searchKeyword, pageable);   //검색 단어가 있는 경우, 검색 키워드 반환
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 9, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/detail/{bno}")
    public String detail(@PathVariable("bno") Integer bno, Model model) {

        Board board = boardService.boardDetail(bno);
        model.addAttribute("board", board);

        return "board/detail";
    }

    // 게시글 글 수정 페이지
    @GetMapping("/modify/{bno}")
    public String showModifyForm(@PathVariable("bno") Integer bno, Model model) {

        Board board = boardService.boardDetail(bno);
        model.addAttribute("board", board);

        return "board/modify";
    }

    // 게시글 수정 - 메시지 - 목록
    @PostMapping("/update/{bno}")
    public String update(@PathVariable("bno") Integer bno, Board updatedBoard, Model model,
                         @RequestParam("file") MultipartFile file) throws IOException {

        Board currentBoard = boardService.boardDetail(bno);
        String message;

        if (currentBoard != null) {

            // 이전 파일 삭제
            if (currentBoard.getFilename() != null) {
                String oldFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static" + currentBoard.getFilepath();
                File oldFile = new File(oldFilePath);
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }

            // 파일 업로드
            try {
                if (!file.isEmpty()) {
                    UUID uuid = UUID.randomUUID();
                    String fileName = uuid + "_" + file.getOriginalFilename();
                    String uploadDirectory = "\\src\\main\\resources\\static\\files"; // 파일을 저장할 디렉토리 경로
                    String filePath = System.getProperty("user.dir") + uploadDirectory + File.separator + fileName;

                    File saveFile = new File(filePath);
                    file.transferTo(saveFile);

                    // 파일이 업로드되었을 때만 파일 정보를 업데이트
                    currentBoard.setFilename(fileName);
                    currentBoard.setFilepath("/files/" + fileName);
                }

                // 게시물 정보 업데이트
                currentBoard.setTitle(updatedBoard.getTitle());
                currentBoard.setContent(updatedBoard.getContent());
                currentBoard.setWriter(updatedBoard.getWriter());
                currentBoard.setUpdateDate(LocalDateTime.now());

                // 글 수정 메시지 처리
                if (boardService.boardUpdate(currentBoard)) {
                    message = "글 수정이 완료되었습니다.";
                } else {
                    message = "글 수정이 실패했습니다.";
                }

            } catch (IOException e) {
                message = "파일 업로드 중 오류가 발생했습니다.";
            }

        } else {
            message = "글 수정이 실패했습니다.";
        }

        model.addAttribute("message", message);
        model.addAttribute("searchUrl", "/board/list");

        return "board/message";

    }

    // 게시글 삭제 - 메시지 - 목록
    @GetMapping("/delete/{bno}")
    public String delete(@PathVariable("bno") Integer bno, Model model) {

        if (boardService.boardDelete(bno)) {
            model.addAttribute("message", "글 삭제가 완료되었습니다.");
        } else {
            model.addAttribute("message", "글 삭제가 실패했습니다.");
        }

        model.addAttribute("searchUrl", "/board/list");

        return "board/message";
    }

}