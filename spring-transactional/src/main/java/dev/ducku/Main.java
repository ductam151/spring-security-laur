package dev.ducku;

import dev.ducku.config.ProjectConfig;
import dev.ducku.repositories.ProductRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            context.getBean("productRepository", ProductRepository.class).addProducts("test");
        }
    }
}
