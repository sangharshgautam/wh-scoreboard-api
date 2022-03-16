package com.sangharsh.oms.service;

import java.util.Map;
import java.util.Optional;

import com.sangharsh.oms.dto.LocalUser;
import com.sangharsh.oms.dto.SignUpRequest;
import com.sangharsh.oms.exception.UserAlreadyExistAuthenticationException;
import com.sangharsh.oms.model.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

public interface UserService {

	public User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

	User findUserByEmail(String email);

	Optional<User> findUserById(String id);

	LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);
}
