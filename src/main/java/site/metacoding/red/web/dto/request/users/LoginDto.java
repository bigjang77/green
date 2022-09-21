package site.metacoding.red.web.dto.request.users;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDto {
	@NotBlank(message = "username이 null이거나 공백일 수 없습니다.")
	private String username;
	@NotBlank(message = "username이 null이거나 공백일 수 없습니다.")
	private String password;
	private boolean remember;
}
