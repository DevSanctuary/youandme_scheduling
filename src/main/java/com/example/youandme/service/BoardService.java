package com.example.youandme.service;

import com.example.youandme.entity.Board;
import com.example.youandme.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void write(Board board) {

        boardRepository.save(board);
    }

    //글 목록 불러오기
    public List<Board> boardList() {

          return boardRepository.findAll();

    }
}
