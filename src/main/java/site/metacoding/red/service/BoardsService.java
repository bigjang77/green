package site.metacoding.red.service;

import org.springframework.stereotype.Service;

@Service
public class BoardsService {//톰캣의 리퀘스트 리스폰스객체는 서비스가 받지않는다=db연결안되면 서비스 만들지말자

	
	public void 게시글목록보기(Integer page, String keyword) {}
	public void 게시글상세보기(Integer id) {}
	public void 게시글수정하기(Integer id) {} //dto
	public void 게시글삭제하기() {}
	public void 게시글쓰기() {}
}
