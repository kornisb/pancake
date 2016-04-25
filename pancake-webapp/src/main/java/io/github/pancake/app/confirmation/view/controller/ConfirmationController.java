package io.github.pancake.app.confirmation.view.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.pancake.app.order.view.model.OrderRequest;

/**
 * Controller class for the confirmation page.
 * @author Bence_Kornis
 */
@Controller
public class ConfirmationController {
    public static final String REQUEST_MAPPING = "/confirmation.html";

    @RequestMapping(value = REQUEST_MAPPING, method = RequestMethod.GET)
    public String confirmation(@Valid @ModelAttribute("orderRequest") OrderRequest orderRequest, BindingResult bindingResult) {
        String result;
        if (bindingResult.hasErrors()) {
            result = "redirect:";
        } else {
            result = "confirmation";
        }
        return result;
    }
}
