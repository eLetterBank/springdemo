package com.example.filters;

import com.example.demo.app.ApplicationProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.shared.constants.Constant.CONTENT_TYPE;
import static com.example.shared.constants.Constant.X_VSOLV_KEY;

@Component
public class AuthenticationFilter extends GenericFilterBean {

    private final static Logger filterLogger = LogManager.getLogger(AuthenticationFilter.class);

    @Autowired
    private ApplicationProperties applicationProperties = null;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        try {
            if (req.getHeader(X_VSOLV_KEY) == null) {
                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
                httpResponse.setContentType(CONTENT_TYPE);
                httpResponse.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
                return;
            } else if (!req.getHeader(X_VSOLV_KEY).contentEquals(applicationProperties.getHttpHeader().getvSolvKey())) {
                HttpServletResponse httpResponse = (HttpServletResponse) servletRequest;
                httpResponse.setContentType(CONTENT_TYPE);
                httpResponse.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
                return;
            }

            filterChain.doFilter(servletRequest, servletResponse);

        } catch (Exception e) {
            filterLogger.error(e.toString());
            filterLogger.error(e);

            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setContentType(CONTENT_TYPE);
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }
}
