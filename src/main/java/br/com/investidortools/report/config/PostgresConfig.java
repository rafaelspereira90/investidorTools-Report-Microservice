package br.com.investidortools.report.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager",
        basePackages = {"br.com.investidortools.report.repository"})
public class PostgresConfig {
    private static final Logger LOG = LoggerFactory.getLogger(PostgresConfig.class);

    @Value(value = "${spring.datasource.password}")
    private String password;

    @Value(value = "${spring.datasource.username}")
    private String user;

    @Value(value = "${spring.datasource.url}")
    private String url;

    @Value(value = "${spring.database.driverClassName}")
    private String driverClassName;

    @Primary
    @Bean(name = "pgDataSource")
    public DataSource dataSource() {
        LOG.info("Inicializando datasource...");
        LOG.info("URL: {}", url);
        LOG.info("User: {}", user);
        LOG.info("Password: {}", "*****");
        return DataSourceBuilder.create().password(/*CriptografiaHelper.decriptaAES128(password)*/password).username(user)
                .driverClassName(driverClassName).url(url).build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("pgDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("br.com.investidortools.report.model").persistenceUnit("postgres")
                .build();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
