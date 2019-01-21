package configuration.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(60);
        System.out.println("Rozpoczynam sesję");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Zakończyłem sesję");
    }
}
