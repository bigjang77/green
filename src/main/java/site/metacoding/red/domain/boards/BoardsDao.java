package site.metacoding.red.domain.boards;

import java.util.List;

public interface BoardsDao {
	public void insert(Boards boards);//save라고 적기도 한다
	public List<Boards> findAll();//selectAll이라고 적기도 한다
	public Boards findById(Integer id);
	public void update(Boards boards);
	public void deleteById(Integer id);
	//CRUD기본
	public void updateByUsersId(Integer id);
	
}
