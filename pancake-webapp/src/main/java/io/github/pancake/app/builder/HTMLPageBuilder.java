package io.github.pancake.app.builder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.github.pancake.persistence.base.Pancake;
import io.github.pancake.service.PancakeService;

/**
 * Builds pancake ordering HTML page.
 * @author Bence_Kornis
 */
@Component
public class HTMLPageBuilder {
    @Value("${order.limit}")
    private int orderLimit;
    @Value("${email.parameter}")
    private String emailParameter;
    @Value("${error.parameter}")
    private String errorParameter;

    @Autowired
    private PancakeService pancakeService;

    /**
     * Builds the HTML page with usage of {@link PrintWriter}
     * @param request Builder uses {@link HttpServletRequest} parameters
     * @param response Builder uses {@link HttpServletResponse} writer
     * @throws IOException when access {@link PrintWriter}
     */
    public void build(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> requestParameters = request.getParameterMap();
        PrintWriter out = response.getWriter();

        createTitle(out);
        createErrorMessage(requestParameters, out);
        createPancakeList(requestParameters, out);
        createEmailField(requestParameters, out);
        createSubmitButton(out);
    }

    private void createEmailField(Map<String, String[]> requestParameters, PrintWriter out) {
        String email = "";
        if (requestParameters.containsKey(emailParameter)) {
            email = requestParameters.get(emailParameter)[0];
        }
        out.println(String.format("<input type=\"text\" name=\"%s\" value=\"%s\" placeholder=\"Add your email address!\" />", emailParameter, email));
    }

    private void createTitle(PrintWriter out) {
        out.println("<html><body>");
        out.println("<h1>Please submit your pancake order!</h1>");
    }

    private void createErrorMessage(Map<String, String[]> requestParameters, PrintWriter out) {
        if (requestParameters.containsKey(errorParameter)) {
            out.println(String.format("<p style=\"color:red\">%s</p>", requestParameters.get(errorParameter)[0]));
        }
    }

    private void createPancakeList(Map<String, String[]> requestParameters, PrintWriter out) {
        out.println("<form action=\"order\" method=\"POST\">");
        for (Pancake pancakeType : pancakeService.getAvailablePancakes()) {
            String name = pancakeType.name();
            int selectedValue = !requestParameters.containsKey(name) ? 0 : Integer.parseInt(requestParameters.get(name)[0]);

            out.println("<p>");
            out.println(pancakeType.name());
            for (int i = 0; i <= orderLimit; i++) {
                out.println(String.format("<input type=\"radio\" name=\"%s\" value=\"%d\" %s />%d", name, i, i == selectedValue ? "checked" : "", i));
            }
            out.println("</p>");
        }
    }

    private void createSubmitButton(PrintWriter out) {
        out.println("<input type=\"submit\" value=\"Submit\" />");
        out.println("</form>");
        out.println("</body></html>");
    }
}
