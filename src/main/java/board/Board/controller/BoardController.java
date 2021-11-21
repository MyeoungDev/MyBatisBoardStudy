package board.Board.controller;

import board.Board.domain.BoardDTO;
import board.Board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/board/write.do")
    public String openBoardWrite(@RequestParam(value = "idx", required = false) Long idx, Model model) {

        if (idx == null) {
            model.addAttribute("board", new BoardDTO());
        } else {
            BoardDTO board = boardService.getBoardDetail(idx);
            if (board == null) {
                return "redirect:/board/list.do";
            }
            model.addAttribute("board", board);
        }

        return "board/write";
    }

    @PostMapping(value = "/board/register.do")
    public String registBoard(final BoardDTO params) {
        try {
            boolean isRegistered = boardService.registerBoard(params);
            if (isRegistered == false) {
                // TODO => 게시슬 등록에 실패했다는 메세지 출력
            }
        } catch (DataAccessException e) {
            // TODO => 데이터베이스 처리 과정에 문제가 발생했다는 메세지 출력
        } catch (Exception e) {
            e.printStackTrace();
            // TODO => 시스템에 문제가 발생했다는 메세지 출력
        }
        return "redirect:/board/list.do";
    }

    @GetMapping(value = "/board/list.do")
    public String openBoardList(Model model) {
        List<BoardDTO> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);

        return "board/list";
    }
}
