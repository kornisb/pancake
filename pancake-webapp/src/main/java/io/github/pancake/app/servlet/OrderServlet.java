package io.github.pancake.app.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import io.github.pancake.app.builder.HTMLPageBuilder;
import io.github.pancake.app.builder.ParameterMapBuilder;
import io.github.pancake.app.builder.UrlBuilder;
import io.github.pancake.app.validation.RequestValidator;

/**
 * Servlet serving pancake ordering form.
 * @author Bence_Kornis
 */
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String ORDER_PATH = "order";
    private static final String CONFIRMATION_PATH = "confirmation";

    @Value("${order.limit}")
    private int orderLimit;
    @Value("${email.parameter}")
    private String emailParameter;
    @Value("${error.parameter}")
    private String errorParameter;

    @Autowired
    private RequestValidator validator;
    @Autowired
    private UrlBuilder urlBuilder;
    @Autowired
    private ParameterMapBuilder parameterMapBuilder;
    @Autowired
    private HTMLPageBuilder htmlPageBuilder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        htmlPageBuilder.build(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String redirectPath;
        if (validator.validate(request)){
            redirectPath = CONFIRMATION_PATH;
        } else {
            redirectPath = createServletUrl(request);
        }
        response.sendRedirect(redirectPath);
    }

    private String createServletUrl(HttpServletRequest request) {
        return urlBuilder.build(ORDER_PATH, createParameters(request));
    }

    private Map<String, String[]> createParameters(HttpServletRequest request) {
        Map<String, String[]> parameters = parameterMapBuilder.createParameterMap(request);
        parameters.put(errorParameter, new String[]{String.format("You can order a maximum of %d pancakes!", orderLimit)});
        return parameters;
    }
}
