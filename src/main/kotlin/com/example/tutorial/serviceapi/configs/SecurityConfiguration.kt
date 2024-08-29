package com.example.tutorial.serviceapi.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration() {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .exceptionHandling { exception ->
                exception
                    .authenticationEntryPoint { exchange, _ ->
                        Mono.fromRunnable { exchange.response.statusCode = HttpStatus.UNAUTHORIZED }
                    }
                    .accessDeniedHandler { exchange, _ ->
                        Mono.fromRunnable { exchange.response.statusCode = HttpStatus.FORBIDDEN }
                    }
            }.headers { headers ->
                headers
                    .contentSecurityPolicy { contentSecurityPolicy ->
                        contentSecurityPolicy.policyDirectives(
                            "script-src 'self' https://trustedscripts.example.com; object-src https://trustedplugins.example.com; report-uri /csp-report-endpoint/"
                        )
                    }
                    .xssProtection { xssProtection ->
                        xssProtection.disable()
                    }
            }
            .csrf().disable()
            .cors().disable()
            .build()
    }
}