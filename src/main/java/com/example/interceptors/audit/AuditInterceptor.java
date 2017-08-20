package com.example.interceptors.audit;

import com.example.shared.models.AuditEvent;
import com.example.shared.utilities.auditlog.AuditLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.UUID;

public class AuditInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(AuditInterceptor.class);

    @Autowired
    private AuditEvent auditEvent = null;

    @Autowired
    private AuditLogger auditLogger = null;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        logger.debug("Pre-handle");

        HandlerMethod hm = (HandlerMethod) o;
        Method method = hm.getMethod();

        if (method.getDeclaringClass().isAnnotationPresent(RestController.class) &&
                method.isAnnotationPresent(Audit.class)) {

            logger.debug(method.getAnnotation(Audit.class).value());

            auditEvent.setHost(httpServletRequest.getRemoteHost());
            auditEvent.setRequestId(UUID.randomUUID().toString());
            auditEvent.setName(method.getAnnotation(Audit.class).value());
            auditEvent.setServiceContract(method.getDeclaringClass().getSimpleName());
            auditEvent.setRequestInTime(LocalDateTime.now());

            for (int i = 0; i < method.getParameterCount(); i++) {
                //Expected only one parameter. If more than one present, then the last one will be recorded
                auditEvent.setOperation(method.getParameters()[i].getType().getSimpleName());
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.debug("Post-handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.debug("AfterCompletion");

        HandlerMethod hm = (HandlerMethod) o;
        Method method = hm.getMethod();

        if (method.isAnnotationPresent(Audit.class)) {
            auditEvent.setResponseOutTime(LocalDateTime.now());
            auditLogger.logEvent(auditEvent);
        }
    }
}
