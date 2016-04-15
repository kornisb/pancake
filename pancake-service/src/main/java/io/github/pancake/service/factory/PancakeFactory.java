package io.github.pancake.service.factory;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import io.github.pancake.persistence.base.Pancake;

/**
 * Factory bean of List of Pancake which contains all possible values of {@link Pancake} enum.
 * @author Bence_Kornis
 */
@Component
public class PancakeFactory implements FactoryBean<List<Pancake>> {
    private final List<Pancake> pancakes = Arrays.asList(Pancake.values());

    @Override
    public List<Pancake> getObject() {
        return pancakes;
    }

    @Override
    public Class<List> getObjectType() {
        return List.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
