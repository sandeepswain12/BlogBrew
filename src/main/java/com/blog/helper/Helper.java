package com.blog.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    static Logger logger = LoggerFactory.getLogger(Helper.class);

    public static String getEmailOfLoggedInUser(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken) {
            var oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            var oAuth2User = (OAuth2User)oAuth2AuthenticationToken.getPrincipal();
            String userName = "";
            if (clientId.equalsIgnoreCase("google")) {
                logger.info("client Id is google");
                userName = oAuth2User.getAttribute("email");
                logger.info("userName is "+userName);
            } else if (clientId.equalsIgnoreCase("github")) {
                logger.info("getEmailOfLoggedInUser: client id is github");
                userName = oAuth2User.getAttribute("email") != null ? oAuth2User.getAttribute("email").toString()
                        : oAuth2User.getAttribute("login").toString() + "@gmail.com";
                logger.info("userName is "+userName);
            }
            return userName;
        }else {
            logger.info("getting data from local database");
            return authentication.getName();
        }
    }
}
