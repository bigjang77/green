package site.metacoding.red.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

@RequiredArgsConstructor
@Service
public class BoardsService {//톰캣의 리퀘스트 리스폰스객체는 서비스가 받지않는다=db연결안되면 서비스 만들지말자

	private final BoardsDao boardsDao;
	
	public String 게시글목록보기(Model model, Integer page, String keyword) {
		if (page == null) {
			page = 0;
		}

		int startNum = page * 3;

		if (keyword == null || keyword.isEmpty()) {
			List<MainDto> boardsList = boardsDao.findAll(startNum);
			PagingDto paging = boardsDao.paging(page, null);

			paging.makeBlockInfo(keyword);

			model.addAttribute("boardsList", boardsList);
			model.addAttribute("paging", paging);

		} else {
			List<MainDto> boardsList = boardsDao.findSearch(startNum, keyword);
			PagingDto paging = boardsDao.paging(page, keyword);
			paging.makeBlockInfo(keyword);
			pagingDto.setmaindtos(boardsList);//
			model.addAttribute("paging", paging);
		}
		return "boards/main";
		
	}
	public void 게시글상세보기(Integer id) {}
	public void 게시글수정하기(Integer id) {} //dto
	public void 게시글삭제하기() {}
	public void 게시글쓰기() {}
}
