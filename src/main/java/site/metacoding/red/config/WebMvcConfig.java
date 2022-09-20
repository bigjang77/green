package site.metacoding.red.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import site.metacoding.red.handler.HelloIntercepter;
import site.metacoding.red.handler.LoginIntercepter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginIntercepter())
		.addPathPatterns("/s/**");//어떤주소일때 인터셉터가 작동하나요, /s/*=>/s/boards, /s/users, /s/boards/1안댐, /s/** 모든게 다됨
		//.addPathPatterns("/admin/**")
		//.excludePathPatterns("/s/boards/**");//제외하기
		registry.addInterceptor(new HelloIntercepter())
		.addPathPatterns("/hello/**");
	}
	
}
