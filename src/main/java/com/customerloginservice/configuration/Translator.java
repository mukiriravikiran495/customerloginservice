package com.customerloginservice.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class Translator {

	private static ResourceBundleMessageSource messageSource;


    @Bean
    @Qualifier("textResourceBundleMessageSource")
    ResourceBundleMessageSource textResourceBundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages"); // name of your properties file (messages.properties)
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
	
	public static String toLocale(String code) {
		Locale locale = LocaleContextHolder.getLocale();
		messageSource.setDefaultEncoding("ISO-8859-1");
		messageSource.setAlwaysUseMessageFormat(true);
		return messageSource.getMessage(code, null, locale);
	}
	
	
	public static String toLocale(String code, String[] args) {
		Locale locale = LocaleContextHolder.getLocale();
		messageSource.setDefaultEncoding("ISO-8859-1");
		messageSource.setAlwaysUseMessageFormat(true);
		return messageSource.getMessage(code, args, locale);
	}
	
}
