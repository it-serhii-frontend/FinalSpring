package com.ua.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class FileConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/imgs/").addResourceLocations("classpath:/lang/**")
				.addResourceLocations("classpath:/static/js/").addResourceLocations("classpath:/static/css/");
	}

	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieDomain("myAppLocaleCookie");
		resolver.setCookieMaxAge(60 * 60);
		return resolver;
	}

	@Bean(name = "messageSource")
	public MessageSource getMessageResource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

	
		messageResource.setBasename("classpath:lang/messages");
		messageResource.setDefaultEncoding("UTF-8");
		return messageResource;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");

		registry.addInterceptor(localeInterceptor).addPathPatterns("/*").addPathPatterns("/admin/**");
	}

}
