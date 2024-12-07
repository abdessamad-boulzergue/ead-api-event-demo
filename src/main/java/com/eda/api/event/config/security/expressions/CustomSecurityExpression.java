package com.eda.api.event.config.security.expressions;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomSecurityExpression {

    public boolean isOwner(Long taskId) {
        String currentUsername = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return "owner".equals(currentUsername);
    }
}