package com.example.testhiber.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.context.annotation.ScopedProxyMode;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionObjectHolder {
    private long amountClick = 0;

    public SessionObjectHolder() {
        System.out.println("Session obj created.");
    }

    public long getAmountClick() {
        return amountClick;
    }
    public void addClick(){
        amountClick++;
    }
}
