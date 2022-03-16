package com.sangharsh.oms.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi apiGroup() {
        return GroupedOpenApi
                .builder()
                .group("Api")
                .pathsToMatch("/api/**")
                .build();
    }
    @Bean
    public OpenAPI apiInfo() {
        final String securitySchemeName = "bearerAuth";
        final String oAuthSecuritySchemeName = "Google Auth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                                .addSecuritySchemes(oAuthSecuritySchemeName,
                                        new SecurityScheme()
                                                .name(oAuthSecuritySchemeName)
                                                .type(SecurityScheme.Type.OAUTH2)
                                                .flows(new OAuthFlows().authorizationCode(
                                                        new OAuthFlow()
                                                                .authorizationUrl("https://accounts.google.com/o/oauth2/v2/auth")
                                                                .tokenUrl("https://www.googleapis.com/oauth2/v4/token")

                                                .scopes( new Scopes()
                                                        .addString("openid", "openid")
                                                        .addString("profile", "profile")
                                                        .addString("email", "email")
                                                )))
                                )
                )
                .info(
                        new Info()
                                .title("Bookstore Rest Api")
                                .description("Rest Api for bookstore web application")
                                .version("1.0")
                );
    }
}
