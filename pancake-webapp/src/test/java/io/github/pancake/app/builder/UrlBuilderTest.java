package io.github.pancake.app.builder;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for {@link UrlBuilder}.
 * @author Bence_Kornis
 */
public class UrlBuilderTest {
    private UrlBuilder underTest;

    @BeforeMethod
    public void setUp() {
        underTest = new UrlBuilder();
    }

    @Test
    public void testBuildShouldReturnUrlFromParametersWhenInvoked() {
        // GIVEN
        String servletPath = "path";
        Map<String, String[]> urlParameters = new HashMap<>();
        urlParameters.put("key1", new String[]{"value11", "value12"});
        urlParameters.put("key2", new String[]{"value21", "value22"});
        // WHEN
        String result = underTest.build(servletPath, urlParameters);
        // THEN
        assertEquals(result, "path?key1=value11,value12&key2=value21,value22");
    }
}
