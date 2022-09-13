package site.metacoding.red.domain.boards;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

public interface BoardsDao {
	public PagingDto paging(@Param("page") Integer page, @Param("keyword") String keyword);
	public void insert(Boards boards);//save라고 적기도 한다
	public List<MainDto> findAll(@Param("startNum") int startNum, @Param("keyword") String keyword);//selectAll이라고 적기도 한다
	public Boards findById(Integer id);
	public void update(Boards boards);
	public void deleteById(Integer id);
	public void updateByUsersId(Integer id);
}
