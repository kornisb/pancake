package io.github.pancake.app.builder;

import java.util.Map;
import java.util.StringJoiner;

import org.springframework.stereotype.Component;

@Component
public class UrlBuilder {
    public String build(String servletPath, Map<String, String[]> urlParameters) {
        StringJoiner parameterJoiner = new StringJoiner("&", "?", "");
        for (Map.Entry<String, String[]> parameter : urlParameters.entrySet()) {
            parameterJoiner.add(createUrlParameter(parameter));
        }
        return new StringBuilder(servletPath).append(parameterJoiner.toString()).toString();
    }

    private StringBuilder createUrlParameter(Map.Entry<String, String[]> parameter) {
        return new StringBuilder(parameter.getKey()).append("=").append(createUrlParameterValues(parameter));
    }

    private String createUrlParameterValues(Map.Entry<String, String[]> parameter) {
        StringJoiner parameterValueJoiner = new StringJoiner(",");
        for (String parameterValue : parameter.getValue()) {
            parameterValueJoiner.add(parameterValue);
        }
        return parameterValueJoiner.toString();
    }
}
