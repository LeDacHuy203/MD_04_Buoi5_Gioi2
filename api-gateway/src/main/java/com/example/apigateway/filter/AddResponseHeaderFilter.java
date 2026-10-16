package com.example.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AddResponseHeaderFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Đăng ký callback beforeCommit để đảm bảo Header luôn được ghi vào Response
        // dù response là 200 OK, 400 Bad Request hay bất kỳ mã trạng thái nào
        exchange.getResponse().beforeCommit(() -> {
            exchange.getResponse().getHeaders().set("X-System-Name", "Api-Gateway-System");
            return Mono.empty();
        });

        // Hậu xử lý qua Post-Filter (then)
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            exchange.getResponse().getHeaders().set("X-System-Name", "Api-Gateway-System");
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
