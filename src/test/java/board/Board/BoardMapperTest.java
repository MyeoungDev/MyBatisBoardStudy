package board.Board;

import board.Board.domain.BoardDTO;
import board.Board.mapper.BoardMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testOfInsert() {
        BoardDTO params = new BoardDTO();
        params.setTitle("1번째 게시글 제목");
        params.setContent("1번째 게시글 내용");
        params.setWriter("테스터");

        int reuslt = boardMapper.insertBoard(params);
        System.out.println("결과는 " + reuslt + " 입니다.");
    }

    @Test
    public void testOfSelectDetail() {
        BoardDTO board = boardMapper.selectBoardDetail((long) 1);
        try {
            String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
//            String boardJson = new ObjectMapper().writeValueAsString(board);

            System.out.println("===============");
            System.out.println("boardJson = " + boardJson);
            System.out.println("===============");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
