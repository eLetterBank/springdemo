package com.example.demo;

import com.example.interceptors.audit.AuditInterceptor;
import com.example.shared.models.AuditEvent;
import com.example.shared.utilities.auditlog.AuditLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@ComponentScan("com.example")
public class DemoApplication extends SpringBootServletInitializer {

	private static final Logger appLogger = LogManager.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//Create Bean method fot "RequestContextListener"
	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	/**
	 * Register a RequestContextListener Bean
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(requestContextListener());
	}

	@EnableWebMvc
	@ComponentScan(basePackages = {"com.springdemo"})
	@Configuration
	public class WebConfig extends WebMvcConfigurerAdapter {
		/**
		 * Enable CORS
		 */
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**");
		}

		//Create Bean method for "AuditInterceptor"
		@Bean
		@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
		AuditInterceptor auditInterceptor() {
			return new AuditInterceptor();
		}

		/**
		 * Add Interceptors
		 */
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(auditInterceptor());
		}

		//AuditEvent per session
		@Bean
		@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
		public AuditEvent auditEvent() {
			return new AuditEvent();
		}

		//AuditLogger per process
		@Bean
		@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
		public AuditLogger auditLog() {
			return new AuditLogger();
		}
	}

}
