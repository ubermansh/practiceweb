package com.binlee.practiceweb;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.sql.SQLOutput;

@RestController
@SpringBootApplication
public class PracticewebApplication {
    private Logger logger = LoggerFactory.getLogger(PracticewebApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(PracticewebApplication.class, args);
    }
    @GetMapping
    public String HelloWorld() {
        logger.info("접속테스트");
        return "Hello World";
    }
}
