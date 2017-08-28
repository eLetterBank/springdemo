package com.example.appframework.http.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GetJsonArgumentResolver implements HandlerMethodArgumentResolver {

    public boolean supportsParameter(MethodParameter methodParameter) {
        GetJsonRequestParam queryAnnotation = methodParameter.getParameterAnnotation(GetJsonRequestParam.class);
        return queryAnnotation != null;
    }

    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        String[] paramVals = request.getParameterValues(methodParameter.getParameterName());
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(paramVals[0], methodParameter.getParameterType());
        } catch (IOException e) {
            return WebArgumentResolver.UNRESOLVED;
        }
    }
}