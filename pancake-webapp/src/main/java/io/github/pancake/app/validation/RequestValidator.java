package io.github.pancake.app.validation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.github.pancake.app.Validator;
import io.github.pancake.persistence.base.Pancake;

/**
 * Validates pancake ordering {@link HttpServletRequest}.
 * @author Bence_Kornis
 */
@Component
public class RequestValidator implements Validator<HttpServletRequest> {
    private final int orderLimit;

    @Autowired
    public RequestValidator(@Value("${order.limit}") int orderLimit) {
        this.orderLimit = orderLimit;
    }

    /**
     * Validation is successful if and only ordered pancake number lower or equal than limit
     */
    @Override
    public boolean validate(HttpServletRequest request) {
        int orderedAmmount = 0;
        for (Pancake pancakeType : Pancake.values()) {
            orderedAmmount += Integer.parseInt(request.getParameter(pancakeType.name()));
        }
        return orderedAmmount <= orderLimit;
    }
}
