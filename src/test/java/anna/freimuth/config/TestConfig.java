package anna.freimuth.config;


import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestConfig {

    @Bean
    @Primary
    public FlywayMigrationStrategy clean() {

        return flyway -> {
            flyway.clean();
            flyway.migrate();
        } ;
    }
}
