package com.example.grantalyticsserver.limiting;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitingFilter implements Filter {

    private final Map<String, AtomicInteger> requestPerIpAddressCounter = new ConcurrentHashMap<>();

    private final AtomicInteger allRequestCounter = new AtomicInteger(0);

    private static final int MAX_REQUESTS_PER_IP = 100;

    private static final int MAX_REQUESTS_FOR_ALL = 500;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String clientIpAddress = httpServletRequest.getRemoteAddr();

        requestPerIpAddressCounter.putIfAbsent(clientIpAddress, new AtomicInteger(0));
        AtomicInteger requestCount = requestPerIpAddressCounter.get(clientIpAddress);

        // Increment the request count
        int requestsFromIp = requestCount.incrementAndGet();
        int requestsFromAll = allRequestCounter.incrementAndGet();

        // Check if the request limit has been exceeded
        if (requestsFromIp > MAX_REQUESTS_PER_IP) {
            httpServletResponse.setStatus(429);
            // Too many requests from this Ip address
            httpServletResponse.getWriter().write("Too many requests. Please try again later.");
            return;
        }

        if(requestsFromAll > MAX_REQUESTS_FOR_ALL){
            httpServletResponse.setStatus(429);
            // Too many requests in general
            httpServletResponse.getWriter().write("Too many requests. Please try again later.");
            return;
        }

        // Allow the request to proceed
        chain.doFilter(request, response);
    }

    private void resetFilterCounters() {
        requestPerIpAddressCounter.clear();
        allRequestCounter.set(0);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        scheduler.scheduleAtFixedRate(
                this::resetFilterCounters, // Clear the map
                0, // Initial delay
                5, // Period (5 minutes)
                TimeUnit.MINUTES // Time unit
        );
    }

    @Override
    public void destroy() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(1, TimeUnit.MINUTES)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
