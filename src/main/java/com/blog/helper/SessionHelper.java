package com.blog.helper;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionHelper {
    public static void removeMessage() {
        try{
            System.out.printf("SessionHelper.removeMessage : ");
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("message");
        } catch (Exception e) {
            System.out.printf("error while removing session attribute: %s\n", e.getMessage());
            e.printStackTrace();
        }
    }
}
