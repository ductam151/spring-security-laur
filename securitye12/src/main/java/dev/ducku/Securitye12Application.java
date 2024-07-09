package dev.ducku;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class Securitye12Application {

    public static void main(String[] args) {
        SpringApplication.run(Securitye12Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            System.out.println("URL Challenge: http://localhost:8080/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=https://springone.io/authorized&code_challenge=mKJFnK7zb97dCzsnNp37n5BNeVjf3jMs_gBbY2LoouI&code_challenge_method=S256");
            System.out.println("Verifier: "+ "JmHZeyPDzmd6tAnx6G13NAXma39_DJHc4QeQmMrzndI");

            /*SecureRandom secureRandom = new SecureRandom();
            byte[] code = new byte[32];
            secureRandom.nextBytes(code);

            String verifier = Base64.getUrlEncoder().withoutPadding().encodeToString(code);
            System.out.println("Verifier: " + verifier);

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digested = messageDigest.digest(verifier.getBytes());
            String challenge = Base64.getUrlEncoder().withoutPadding().encodeToString(digested);
            System.out.println("Challenge: " + challenge);
             */
        };
    }
}
