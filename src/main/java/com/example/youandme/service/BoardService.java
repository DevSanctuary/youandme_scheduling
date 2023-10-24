package com.example.youandme.service;

import com.example.youandme.entity.Board;
import com.example.youandme.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 등록 및 파일 업로드(파일 업로드 저장 예외 상황 처리 : IOException)
    public boolean register(Board board, MultipartFile file) throws IOException {

        //파일을 저장할 디렉토리 경로 설정
        String projectPath = System.getProperty("user.dir");                // 현재 프로젝트의 디렉토리를 가져옴
        String uploadDirectory = "\\src\\main\\resources\\static\\files";   // 파일을 저장할 디렉토리 경로

        //고유한 파일명 생성(식별)
        UUID uuid = UUID.randomUUID();
        String originalFilename = file.getOriginalFilename();
        String fileName = uuid + "_" + originalFilename; // 임의의난수_원래파일명 - 파일명에 UUID를 추가하여 중복 방지

        //저장될 파일 경로 설정(File.separator : OS에 맞는 파일 경로 구분자를 사용)
        String filePath = projectPath + uploadDirectory + File.separator + fileName;

        //업로드된 파일을 저장할 File 객체 생성
        File saveFile = new File(filePath);

        //업로드된 파일을 서버에 저장
        file.transferTo(saveFile);

        //Board 엔티티에 파일 이름과 경로 저장
        board.setFilename(fileName);                // 파일명 저장
        board.setFilepath("/files/" + fileName);    // 웹에서 접근할 수 있는 파일 경로

        //게시물 등록 시간 설정
        board.setRegDate(LocalDateTime.now());
        board.setUpdateDate(LocalDateTime.now());

        //게시물 정보를 DB에 저장
        boardRepository.save(board);

        return true;
    }

    // 게시글 목록 조회
    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    // 게시글 상세 조회
    public Board boardDetail(Integer bno) {
        return boardRepository.findById(bno).orElse(null);
    }

    // 게시글 수정
    public boolean boardUpdate(Board board) {
        boardRepository.save(board);
        return true;
    }

    // 게시글 삭제
    public boolean boardDelete(Integer bno) {

        Board board = boardRepository.findById(bno).orElse(null);

        if (board != null) {
            // 첨부 파일이 있는 경우 파일 삭제
            if (board.getFilename() != null) {
                String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static" + board.getFilepath();
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                }
            }

            boardRepository.deleteById(bno);
            return true;
        }

        return false;
    }
}