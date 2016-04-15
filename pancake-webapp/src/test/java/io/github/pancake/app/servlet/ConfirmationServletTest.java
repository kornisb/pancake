package io.github.pancake.app.servlet;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.PrintWriter;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.pancake.service.PancakeService;

/**
 * Test class for {@link ConfirmationServlet}.
 * @author Bence_Kornis
 */
public class ConfirmationServletTest {
    @InjectMocks
    private ConfirmationServlet underTest;
    @Mock
    private HttpServletRequest mockRequest;
    @Mock
    private HttpServletResponse mockResponse;
    @Mock
    private PrintWriter mockPrintWriter;
    @Mock
    private PancakeService mockPancakeService;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testDoGetShouldPrintStaticContentWhenInvoked() throws Exception {
        // GIVEN
        when(mockResponse.getWriter()).thenReturn(mockPrintWriter);
        when(mockPancakeService.getAvailablePancakes()).thenReturn(Collections.emptyList());
        // WHEN
        underTest.doGet(mockRequest, mockResponse);
        // THEN
        verify(mockPrintWriter, times(3)).println(anyString());
    }
}
