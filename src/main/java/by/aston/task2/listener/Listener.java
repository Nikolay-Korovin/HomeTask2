package by.aston.task2.listener;

import lombok.SneakyThrows;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class Listener implements HttpSessionListener, HttpSessionAttributeListener, ServletContextListener {
    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce){
        Class.forName("org.postgresql.Driver");
    }

}
