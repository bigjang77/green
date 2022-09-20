package site.metacoding.red.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.red.web.dto.response.CMRespDto;

public class HelloIntercepter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		BufferedReader br = request.getReader();
		StringBuilder sb = new StringBuilder();
		while (true) {
			String temp = br.readLine();
			if (temp == null)
				break;
			sb.append(temp);
		}
		
		if (sb.toString().contains("바보")) {
            response.setContentType("application/json; charset=utf-8");//컨텐트타입을 json으로 utf-8로 설정
            PrintWriter out = response.getWriter();//버퍼트라이트 스프링식으로
            CMRespDto<?> cmRespDto = new CMRespDto<>(-1, "그만", null);//json타입으로 보낸다
            ObjectMapper om = new ObjectMapper();//자바객체를 json타입으로 변환하거나 반대도한다
            String json = om.writeValueAsString(cmRespDto);
            out.println(json);
			return false;
		}
		return true;
	}

}
