package com.assj5.thien.assj5;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionListener implements HttpSessionListener {

    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        counter.incrementAndGet();  //incrementing the counter
        updateSessionCounter(se);
        se.getSession().setMaxInactiveInterval(5*60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        counter.decrementAndGet();  //decrementing counter
        updateSessionCounter(se);
    }

    private void updateSessionCounter(HttpSessionEvent httpSessionEvent){
        //Let's set in the context
        httpSessionEvent.getSession().getServletContext()
                .setAttribute("activeSession", counter.get());
    }

}
