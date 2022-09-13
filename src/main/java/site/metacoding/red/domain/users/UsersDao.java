package site.metacoding.red.domain.users;

import java.util.List;


public interface UsersDao {
	public void insert(Users users);//save라고 적기도 한다
	public List<Users> findAll();//selectAll이라고 적기도 한다
	public Users findById(Integer id);
	public void update(Users users);
	public void deleteById(Integer id);
	public Users findByUsername(String username);
}
