package io.github.pancake.app.validation;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import javax.servlet.http.HttpServletRequest;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.pancake.persistence.base.Pancake;

/**
 * Test class for {@link RequestValidator}.
 * @author Bence_Kornis
 */
public class RequestValidatorTest {
    private static final int LIMIT = Pancake.values().length;
    private RequestValidator underTest;
    @Mock
    private HttpServletRequest mockRequest;

    @BeforeMethod
    public void setUp() {
        underTest = new RequestValidator(LIMIT);
        initMocks(this);
    }

    @Test
    public void testValidateShouldReturnTrueWhenInvokedWithValidParameters() {
        // GIVEN
        when(mockRequest.getParameter(anyString())).thenReturn(String.valueOf(1));
        // WHEN
        boolean result = underTest.validate(mockRequest);
        // THEN
        assertTrue(result);
    }

    @Test
    public void testValidateShouldReturnFalseWhenInvokedWithInvalidParameters() {
        // GIVEN
        when(mockRequest.getParameter(anyString())).thenReturn(String.valueOf(2));
        // WHEN
        boolean result = underTest.validate(mockRequest);
        // THEN
        assertFalse(result);
    }
}
