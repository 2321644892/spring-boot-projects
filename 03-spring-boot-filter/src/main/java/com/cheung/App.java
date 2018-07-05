package com.cheung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Spring Boot整合Filter 方式一: -->
 *      1.在Filter类上添加@WebFilter(filterName = "", urlPatterns = "")注解扫描完成注册
 *      2.在启动类上添加@ServletComponentScan注解(Servlet组件扫描)
 *
 * @author Cheung
 */
@SpringBootApplication
@ServletComponentScan
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
