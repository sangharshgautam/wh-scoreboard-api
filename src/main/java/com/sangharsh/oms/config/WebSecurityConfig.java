package com.sangharsh.oms.config;

import java.util.Arrays;
import java.util.Optional;

import com.sangharsh.oms.dto.LocalUser;
import com.sangharsh.oms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.sangharsh.oms.security.jwt.TokenAuthenticationFilter;
import com.sangharsh.oms.security.oauth2.CustomOAuth2UserService;
import com.sangharsh.oms.security.oauth2.CustomOidcUserService;
import com.sangharsh.oms.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.sangharsh.oms.security.oauth2.OAuth2AccessTokenResponseConverterWithDefaults;
import com.sangharsh.oms.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.sangharsh.oms.security.oauth2.OAuth2AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static java.lang.String.format;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${springdoc.api-docs.path}")
	private String restApiDocPath;
	@Value("${springdoc.swagger-ui.path}")
	private String swaggerPath;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	@Autowired
	CustomOidcUserService customOidcUserService;

	@Autowired
	private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

	@Autowired
	private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.headers().frameOptions().disable().and()
			.cors()
				.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.csrf().disable()
			.formLogin().disable()
			.httpBasic().disable()
			.exceptionHandling()
				.authenticationEntryPoint(new RestAuthenticationEntryPoint())
				.and()
			.authorizeRequests()
				// Swagger endpoints must be publicly accessible
				.antMatchers("/").permitAll()
				.antMatchers(format("%s/**", "/ws")).permitAll()
				.antMatchers(format("%s/**", "/app")).permitAll()
				.antMatchers(format("%s/**", restApiDocPath)).permitAll()
				.antMatchers(format("%s/**", swaggerPath)).permitAll()
				.antMatchers(format("%s/**", "/h2-console")).permitAll()
				.antMatchers("/", "/error", "/api/all", "/api/auth/**", "/oauth2/**", "/actuator/**").permitAll()
			.anyRequest()
				.authenticated()
				.and()
			.oauth2Login()
				.authorizationEndpoint()
					.authorizationRequestRepository(cookieAuthorizationRequestRepository())
					.and()
				.redirectionEndpoint()
					.and()
				.userInfoEndpoint()
					.oidcUserService(customOidcUserService)
					.userService(customOAuth2UserService)
					.and()
				.tokenEndpoint()
					.accessTokenResponseClient(authorizationCodeTokenResponseClient())
					.and()
				.successHandler(oAuth2AuthenticationSuccessHandler)
				.failureHandler(oAuth2AuthenticationFailureHandler);

		// Add our custom Token based authentication filter
		http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter();
	}

	/*
	 * By default, Spring OAuth2 uses
	 * HttpSessionOAuth2AuthorizationRequestRepository to save the authorization
	 * request. But, since our service is stateless, we can't save it in the
	 * session. We'll save the request in a Base64 encoded cookie instead.
	 */
	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}

	// This bean is load the user specific data when form login is used.
	@Override
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> authorizationCodeTokenResponseClient() {
		OAuth2AccessTokenResponseHttpMessageConverter tokenResponseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
		tokenResponseHttpMessageConverter.setTokenResponseConverter(new OAuth2AccessTokenResponseConverterWithDefaults());
		RestTemplate restTemplate = new RestTemplate(Arrays.asList(new FormHttpMessageConverter(), tokenResponseHttpMessageConverter));
		restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
		DefaultAuthorizationCodeTokenResponseClient tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
		tokenResponseClient.setRestOperations(restTemplate);
		return tokenResponseClient;
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		return () -> {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = null;
			if (authentication != null && authentication.getPrincipal() instanceof User) {
				user = (User) authentication.getPrincipal();
			}
			if (authentication != null && authentication.getPrincipal() instanceof LocalUser) {
				LocalUser localUser = (LocalUser) authentication.getPrincipal();
				user = localUser.getUser();
			}
			return Optional.ofNullable(user).map(User::getId);
		};
	}
}
