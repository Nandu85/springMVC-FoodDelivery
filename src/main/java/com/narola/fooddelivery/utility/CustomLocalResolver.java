package com.narola.fooddelivery.utility;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class CustomLocalResolver implements LocaleResolver {

    private static String lang;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        if (request.getParameter("lang") != null) {
//            return Locale.getDefault();
            lang = request.getParameter("lang");
        }
        else if (lang == null)
            lang = "en";
            return new Locale(lang);
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        if (!response.isCommitted()) {
            response.setLocale(locale);
            response.setCharacterEncoding("UTF-8");
        }
    }
}
