package site.metacoding.red.domain.loves;

import java.util.List;



public interface LovesDao {
	public void insert(Loves loves);//save라고 적기도 한다
	public List<Loves> findAll();//selectAll이라고 적기도 한다
	public Loves findById(Integer id);
	public void update(Loves loves);
	public void deleteById(Integer id);
}
