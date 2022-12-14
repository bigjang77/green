package site.metacoding.red.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.loves.Loves;
import site.metacoding.red.domain.loves.LovesDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.handler.ex.MyException;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.DetailDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;
import site.metacoding.red.web.dto.response.loves.LovesDto;

@RequiredArgsConstructor
@Service
public class BoardsService {

	private final BoardsDao boardsDao;
	private final LovesDao lovesDao;

	public Loves 좋아요(Loves loves) {
		lovesDao.insert(loves);
		return loves;
	}

	public void 좋아요취소(Integer lovesId) {
		lovesDao.deleteById(lovesId);
	}

	public PagingDto 게시글목록보기(Integer page, String keyword) {
		if (page == null) {
			page = 0;
		}
		int startNum = page * PagingDto.ROW;
		List<MainDto> boardsList = boardsDao.findAll(startNum, keyword, PagingDto.ROW);
		PagingDto pagingDto = boardsDao.paging(page, keyword, PagingDto.ROW);
		if (boardsList.size() == 0)
			pagingDto.setNotResult(true);
		pagingDto.makeBlockInfo(keyword);
		pagingDto.setMainDtos(boardsList);

		return pagingDto;
	}

	public DetailDto 게시글상세보기(Integer id, Integer principalId) {
		return boardsDao.findByDetail(id, principalId);
	}

	public Boards 게시글수정화면데이터가져오기(Integer id) {
		
		Boards boards = boardsDao.findById(id);
		
		if(boards == null) {
			throw new MyException(id+"의 게시글을 찾을 수 없습니다");
		}
		
		return boards;
	}

	public Boards 게시글수정하기(Integer id, UpdateDto updateDto) {
		// 1. 영속화
		Boards boardsPS = boardsDao.findById(id);

		if (boardsPS == null) {
			throw new RuntimeException(id+"의 게시글을 찾을 수 없습니다");
		}

		// 2. 변경
		boardsPS.update(updateDto);

		// 3. 수행
		boardsDao.update(boardsPS);

		return boardsPS;
	}

	public void 게시글삭제하기(Integer id) {
		Boards boardsPS = boardsDao.findById(id);

		if (boardsPS == null) {
		}

		boardsDao.deleteById(id);
	}

	public void 게시글쓰기(WriteDto writeDto, Users principal) {
		boardsDao.insert(writeDto.toEntity(principal.getId()));
	}

}