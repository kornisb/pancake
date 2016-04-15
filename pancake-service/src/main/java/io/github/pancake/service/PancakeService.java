package io.github.pancake.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pancake.persistence.base.Pancake;
import io.github.pancake.service.factory.PancakeFactory;

/**
 * Service to get {@link Pancake} enum types.
 * @author Bence_Kornis
 */
@Service
public class PancakeService {
    private final PancakeFactory pancakeFactory;

    @Autowired
    public PancakeService(PancakeFactory pancakeFactory) {
        this.pancakeFactory = pancakeFactory;
    }

    public List<Pancake> getAvailablePancakes() {
        return pancakeFactory.getObject();
    }
}
