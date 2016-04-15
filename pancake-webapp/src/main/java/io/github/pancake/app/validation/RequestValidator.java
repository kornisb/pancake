package io.github.pancake.app.validation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.github.pancake.app.Validator;
import io.github.pancake.persistence.base.Pancake;

@Component
public class RequestValidator implements Validator<HttpServletRequest> {
    private final int orderLimit;

    @Autowired
    public RequestValidator(@Value("${order.limit}") int orderLimit) {
        this.orderLimit = orderLimit;
    }

    @Override
    public boolean validate(HttpServletRequest request) {
        int orderedAmmount = 0;
        for (Pancake pancakeType : Pancake.values()) {
            orderedAmmount += Integer.parseInt(request.getParameter(pancakeType.name()));
        }
        return orderedAmmount <= orderLimit;
    }
}
