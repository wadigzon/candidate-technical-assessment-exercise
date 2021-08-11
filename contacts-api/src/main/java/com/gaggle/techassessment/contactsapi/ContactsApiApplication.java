package com.gaggle.techassessment.contactsapi;


import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@EnableJpaRepositories("com.gaggle.techassessment.contactsapi.repo")
@EntityScan("com.gaggle.techassessment.contactsapi.model")
@SpringBootApplication
@Configuration
public class ContactsApiApplication {
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(ContactsApiApplication.class);
	}	
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8084", "-ifNotExists");
    }	
	public static void main(String[] args) {
		SpringApplication.run(ContactsApiApplication.class, args);
	}
}
