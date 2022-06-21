package com.narola.fooddelivery.utility;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(URLException.class)
    public ModelAndView handleURLException(URLException eu) {
        ModelAndView modelAndView = new ModelAndView("ErrorPage");
        modelAndView.addObject("ErrMsg", eu.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception e) {
        ModelAndView modelAndView = new ModelAndView("ErrorPage");
        modelAndView.addObject("ErrMsg", e.getMessage());
        modelAndView.addObject("ErrType", e.getClass());
        return modelAndView;
    }
}
