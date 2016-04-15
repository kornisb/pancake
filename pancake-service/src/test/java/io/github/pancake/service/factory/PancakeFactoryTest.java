
package io.github.pancake.service.factory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.pancake.persistence.base.Pancake;

/**
 * Test class for {@link PancakeFactory}.
 * @author Bence_Kornis
 */
public class PancakeFactoryTest {
    private PancakeFactory underTest;

    @BeforeMethod
    public void setUp() {
        underTest = new PancakeFactory();
    }

    @Test
    public void testGetObjectShouldReturnPancakeTypesWhenInvoked() {
        // GIVEN
        List<Pancake> pancakeTypes = new ArrayList<>(Arrays.asList(Pancake.values()));
        // WHEN
        List<Pancake> pancakeTypesFromFactory = underTest.getObject();
        // THEN
        assertEquals(pancakeTypes.size(), pancakeTypesFromFactory.size());
        assertTrue(pancakeTypesFromFactory.containsAll(pancakeTypes));
    }
}
