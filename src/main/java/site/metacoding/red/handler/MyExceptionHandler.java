package site.metacoding.red.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import site.metacoding.red.handler.ex.MyApiException;
import site.metacoding.red.handler.ex.MyException;
import site.metacoding.red.utill.Script;
import site.metacoding.red.web.dto.response.CMRespDto;


@ControllerAdvice//에러만 전담처리하는 컨트롤러
public class MyExceptionHandler {
	
	@ExceptionHandler(MyApiException.class)
	public @ResponseBody CMRespDto<?> apiError(Exception e){//오류가나면 이 메서드가 때려진다
		return new CMRespDto<>(-1, e.getMessage(), null);
	}
	
	@ExceptionHandler(MyException.class)
	public @ResponseBody String Error(Exception e){//오류가나면 이 메서드가 때려진다
		return Script.back(e.getMessage());
	}
}

