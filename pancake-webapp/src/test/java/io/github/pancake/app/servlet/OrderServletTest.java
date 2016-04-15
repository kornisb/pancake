package io.github.pancake.app.servlet;

import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.pancake.app.builder.HTMLPageBuilder;
import io.github.pancake.app.builder.ParameterMapBuilder;
import io.github.pancake.app.builder.UrlBuilder;
import io.github.pancake.app.validation.RequestValidator;

public class OrderServletTest {
    private static final String ORDER_PATH = "order";
    private static final String CONFIRMATION_PATH = "confirmation";

    @InjectMocks
    private OrderServlet underTest;
    @Mock
    private HttpServletRequest mockRequest;
    @Mock
    private HttpServletResponse mockResponse;
    @Mock
    private RequestValidator mockValidator;
    @Mock
    private UrlBuilder mockUrlBuilder;
    @Mock
    private ParameterMapBuilder mockParameterMapBuilder;
    @Mock
    private HTMLPageBuilder mockHTMLPageBuilder;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testDoGetShouldUseHtmlPageBuilderWhenInvoked() throws IOException {
        // GIVEN in setUp
        // WHEN
        underTest.doGet(mockRequest, mockResponse);
        // THEN
        verify(mockHTMLPageBuilder, only()).build(mockRequest, mockResponse);
    }

    @Test
    public void testDoPostShouldRedirectToConfirmationPageWhenValidationSucceded() throws IOException {
        // GIVEN
        when(mockValidator.validate(mockRequest)).thenReturn(true);
        // WHEN
        underTest.doPost(mockRequest, mockResponse);
        // THEN
        verify(mockResponse, only()).sendRedirect(CONFIRMATION_PATH);
    }

    @Test
    public void testDoPostShouldRedirectToOrderPageWhenValidationFailed() throws IOException {
        // GIVEN
        Map<String, String[]> parameterMap = new HashMap<>();
        String url = new String();
        when(mockValidator.validate(mockRequest)).thenReturn(false);
        when(mockParameterMapBuilder.createParameterMap(mockRequest)).thenReturn(parameterMap);
        when(mockUrlBuilder.build(eq(ORDER_PATH), anyMapOf(String.class, String[].class))).thenReturn(url);
        // WHEN
        underTest.doPost(mockRequest, mockResponse);
        // THEN
        verify(mockParameterMapBuilder, only()).createParameterMap(mockRequest);
        verify(mockUrlBuilder, only()).build(ORDER_PATH, parameterMap);
        verify(mockResponse, only()).sendRedirect(url);
    }
}
