package io.danielegradassai;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Configuration
public class MessageConfig {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("it"));
        return sessionLocaleResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); // Assicurati che i tuoi file di messaggi siano nella directory "resources" del tuo progetto
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public ResourceBundleMessageSource errMessage() {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setBasename("errMessage"); // Specifica il nome base del file di messaggi degli errori
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }
}

