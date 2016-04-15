package io.github.pancake;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.pancake.service.PancakeService;
import io.github.pancake.service.factory.PancakeFactory;

/**
 * Test class for {@link PancakeService}.
 *
 * @author Bence_Kornis
 */
public class PancakeServiceTest {
    @InjectMocks
    private PancakeService underTest;
    @Mock
    private PancakeFactory mockPancakeFactory;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetPancakesShouldReturnAvailablePancakesWhenInvoked() {
        // GIVEN in setUp
        // WHEN
        underTest.getAvailablePancakes();
        // THEN
        verify(mockPancakeFactory,only()).getObject();
    }
}
