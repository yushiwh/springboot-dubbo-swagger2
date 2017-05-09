package com.jztey.gxdemo;

import com.jztey.framework.boot.ApplicationDruid;
import com.jztey.framework.boot.ApplicationDubbo;
import com.jztey.framework.boot.ApplicationInterfaceMvc;
import com.jztey.framework.boot.ApplicationMonitoring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by yushi on 2016/8/8.
 */
 
@SpringBootApplication
@Import({ ApplicationInterfaceMvc.class //
		, ApplicationCaching.class
		, ApplicationDubbo.class// 导入dubbo
		, ApplicationDruid.class// 导入Druid
		, ApplicationMonitoring.class }) 
 
public class Application   {

	 
	@Bean
	public WebMvcConfigurer corsConfigurer() { // 允许跨域
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "DELETE", "PUT",
						"OPTIONS");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	 

}
