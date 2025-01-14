package com.example.grantalyticsserver;

import com.example.grantalyticsserver.limiting.RateLimitingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GrantalyticsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrantalyticsServerApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<RateLimitingFilter> applyRateLimitingFilter() {
		FilterRegistrationBean<RateLimitingFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new RateLimitingFilter());
		registrationBean.addUrlPatterns("/api/*"); // Register filter for API endpoints
		return registrationBean;
	}
}
