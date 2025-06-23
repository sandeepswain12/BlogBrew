package com.blog.config;

import com.blog.entity.Providers;
import com.blog.entity.User;
import com.blog.helper.AppConstants;
import com.blog.repository.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepo userRepo;

    Logger logger = LoggerFactory.getLogger(OAuthSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("Authentication Success Handler");
        var oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        logger.info("authorizedClientRegistrationIdId: " + authorizedClientRegistrationId);
        var oAuthUser = (DefaultOAuth2User) authentication.getPrincipal();

        oAuthUser.getAttributes().forEach((key, value) -> {
            logger.info("{} => {}", key, value);
        });

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setEnabled(true);
        user.setEmailVerified(true);
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setPassword("dummy");

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {
            user.setEmail(oAuthUser.getAttribute("email"));
            user.setName(oAuthUser.getAttribute("name"));
            user.setProviderUserId(oAuthUser.getName());
            user.setProviders(Providers.GOOGLE);
        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {
            String email = oAuthUser.getAttribute("email") != null ? oAuthUser.getAttribute("email")
                    : oAuthUser.getAttribute("login") + "@gmail.com";
            String name = oAuthUser.getAttribute("login");
            String providerUserId = oAuthUser.getName();
            user.setEmail(email);
            user.setName(name);
            user.setProviderUserId(providerUserId);
            user.setProviders(Providers.GITHUB);
            user.setProviderUserId(providerUserId);
        } else {
            logger.error("Unauthorized Client Registration Id");
        }

        User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
        if (user2 == null) {
            userRepo.save(user);
            logger.info("User saved");
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }
}
