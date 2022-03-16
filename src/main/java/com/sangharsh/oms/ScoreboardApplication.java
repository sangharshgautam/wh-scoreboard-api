package com.sangharsh.oms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@OpenAPIDefinition(
		tags = {
				@Tag(name="widget", description="Widget operations."),
				@Tag(name="gasket", description="Operations related to gaskets")
		},
		info = @Info(
				title="Order Management System API",
				version = "1.0.1",
				contact = @Contact(
						name = "OMS API Support",
						url = "https://exampleurl.com/contact",
						email = "techsupport@example.com"),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)
//@SecuritySchemes(
//		{
//				@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT"),
//				@SecurityScheme(name = "Google Auth", type = SecuritySchemeType.OAUTH2, flows = @OAuthFlows(implicit =
//				@OAuthFlow(
//						authorizationUrl = "https://accounts.google.com/o/oauth2/v2/auth",
//						tokenUrl = "https://www.googleapis.com/oauth2/v4/token",
//						scopes = {
//								@OAuthScope(name = "openid", description = "Open id"),
//								@OAuthScope(name = "profile", description = "Profile"),
//								@OAuthScope(name = "email", description = "Email")}
//				)))
//		}
//)
@SpringBootApplication
@EnableCaching
public class ScoreboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScoreboardApplication.class, args);
	}

}
