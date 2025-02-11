package com.example.grantalyticsserver;

import com.example.grantalyticsserver.dto.GenerateLogIdResponseDto;
import com.example.grantalyticsserver.dto.LogEventDto;
import com.example.grantalyticsserver.limiting.RateLimitingFilter;
import com.example.grantalyticsserver.model.LogEvent;
import com.example.grantalyticsserver.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@RestController
	@RequestMapping("/api")
	@CrossOrigin(origins = "http://localhost:3000")
	public static class GrantalyticsController {

		private final LoggingService loggingService;
		@Autowired
		public GrantalyticsController(LoggingService loggingService) {
			this.loggingService = loggingService;
		}

		// Existing endpoint to create a product and update materials
		@PostMapping("/log-visit")
		@CrossOrigin(origins = "*")
		public ResponseEntity<String> logVisit(@RequestBody LogEventDto logEventDto) {
			LogEvent logEvent = loggingService.logEvent(logEventDto);
			if(logEvent == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Visit Successfully Logged.", HttpStatus.OK);
		}

		@GetMapping("/generate")
		@CrossOrigin(origins = "*")
		public ResponseEntity<GenerateLogIdResponseDto> generate() {
			GenerateLogIdResponseDto logId = loggingService.generateLog();
			if(logId == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity.ok(logId);
		}

	}
}
