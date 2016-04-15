package io.github.pancake.app.builder;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for {@link ParameterMapBuilder}.
 * @author Bence_Kornis
 */
public class ParameterMapBuilderTest {
    private static final String KEY = "key";
    private static final String VALUE = "value";

    private ParameterMapBuilder underTest;
    @Mock
    private HttpServletRequest mockRequest;

    @BeforeMethod
    public void setUp() {
        underTest = new ParameterMapBuilder();
        initMocks(this);
    }

    @Test
    public void testCreateParameterMapShouldReturnRequestParameterEntryWhenInvoked() {
        // GIVEN
        Map<String, String[]> requestParameterMap = new HashMap<>();
        requestParameterMap.put(KEY, new String[]{VALUE});
        when(mockRequest.getParameterMap()).thenReturn(requestParameterMap);
        // WHEN
        Map<String, String[]> resultMap = underTest.createParameterMap(mockRequest);
        // THEN
        assertTrue(resultMap.containsKey(KEY));
        assertEquals(resultMap.entrySet().size(), 1);
        String[] values = resultMap.get(KEY);
        assertEquals(values.length, 1);
        assertEquals(values[0], VALUE);
    }
}
