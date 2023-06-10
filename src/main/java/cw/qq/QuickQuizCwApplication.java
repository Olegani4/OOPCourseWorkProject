package cw.qq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class QuickQuizCwApplication {

    // Configuring static resources
    @Configuration
    public class WebConfig implements WebMvcConfigurer {

        // This method is used to configure static resources such as images, css, js, etc.
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**")
                    .addResourceLocations("classpath:/static/");
        }

    }

    // This is the main method that runs the application
    public static void main(String[] args) {
        SpringApplication.run(QuickQuizCwApplication.class, args);
    }

}
