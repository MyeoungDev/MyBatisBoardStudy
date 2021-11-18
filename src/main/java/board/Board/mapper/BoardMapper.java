package board.Board.mapper;

import board.Board.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    // 게시글 추가
    public int insertBoard(BoardDTO params);
    // 게시글 한개 선택
    public BoardDTO selectBoardDetail(Long idx);
    // 게시글 수정
    public int updateBoard(BoardDTO params);
    // 게시글 삭제
    public int deleteBoards(Long idx);
    // 게시글 전체 조회
    public List<BoardDTO> selectBoardList();
    // 전체 게시글 수
    public int selectBoardTotalCount();

}
