package com.ecommerce.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            // User Service routes
            .route("user-service", r -> r.path("/api/users/**")
                .uri("lb://user-service"))
            
            // Product Service routes
            .route("product-service", r -> r.path("/api/products/**", "/api/categories/**")
                .uri("lb://product-service"))
            
            // Order Service routes
            .route("order-service", r -> r.path("/api/orders/**", "/api/cart/**")
                .uri("lb://order-service"))
            
            // Payment Service routes
            .route("payment-service", r -> r.path("/api/payments/**")
                .uri("lb://payment-service"))
            
            // Inventory Service routes
            .route("inventory-service", r -> r.path("/api/inventory/**")
                .uri("lb://inventory-service"))
            
            .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOriginPattern("*");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
