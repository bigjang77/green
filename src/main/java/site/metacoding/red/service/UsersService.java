package site.metacoding.red.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;

@RequiredArgsConstructor
@Service
public class UsersService {// 톰캣의 리퀘스트 리스폰스객체는 서비스가 받지않는다=db연결안되면 서비스 만들지말자

	private final UsersDao usersDao;
	private final BoardsDao boardsDao;

	public void 회원가입(JoinDto joinDto) {// username, password, email dto 작성
		// 1.dto를 엔티티로 변경하는 코드
		Users users = joinDto.toEntity();
		// 2.엔티티로 디비 수행
		usersDao.insert(users);
	}

	public Users 로그인(LoginDto loginDto) {// username, password
		Users usersPS = usersDao.findByUsername(loginDto.getUsername());

		// if로 usersPS의 password와 디티오.password 비교
		if (usersPS.getPassword().equals(loginDto.getPassword()) ) {
			return usersPS;
		}else {
			return null;
		}
	}

	public void 회원수정(Integer id, UpdateDto updateDto) {// id, dto(password,email)
		// 1.영속화
		Users usersPS = usersDao.findById(id);
		// 2.영속화된 객체 변경
		usersPS.update(updateDto);
		// 3.디비 수행
		usersDao.update(usersPS);
	}

	@Transactional(rollbackFor = RuntimeException.class) // 수행실패햇을때 롤백해준다
	public void 회원탈퇴(Integer id) {// user-id,
		usersDao.deleteById(id);

		boardsDao.updateByUsersId(id);
		// 해당회원이적은 글을 모두 찾아서 usersId를 null로 업데이트();
	} // users-delete, boards-update, 마이바티스 포문돌리면서 업데이트

	public boolean 유저네임중복확인(String username) { 
		Users usersPS = usersDao.findByUsername(username);
		
		//잇으면 트루,없으면 false리턴
		if(usersPS == null) {
			return false;
		}else {
			return true;
		}
	}

	public Users 회원정보보기(Integer id) {
		Users usersPS = usersDao.findById(id);
		return usersPS;
	}

}
