@Configuration
class SecurityConfiguration {
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.authorizeExchange()
                .pathMatchers("/**")
                    .permitAll()
                .and()
                .httpBasic()
                    .disable()
                .csrf()
                    .disable()
                .formLogin()
                    .disable()
                .logout()
                    .disable()
                .build()
    }
}