package com.example.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Lấy thông tin Path của Request
        String path = exchange.getRequest().getPath().toString();

        // In ra Console đúng định dạng yêu cầu
        System.out.println("Incoming request to:" + path);
        log.info("Incoming request to:{}", path);

        // Cho phép Request đi tiếp đến Service đích
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // Độ ưu tiên của filter (chạy sớm nhất khi request đến)
        return 0;
    }
}
