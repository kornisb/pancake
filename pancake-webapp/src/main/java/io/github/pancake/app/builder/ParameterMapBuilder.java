package io.github.pancake.app.builder;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class ParameterMapBuilder {
    public Map<String, String[]> createParameterMap(HttpServletRequest request) {
        Map<String, String[]> parameterMap = new HashMap<>();
        for (Map.Entry<String, String[]> parameter : request.getParameterMap().entrySet()) {
            parameterMap.put(parameter.getKey(), parameter.getValue());
        }
        return parameterMap;
    }
}