package com.techsharif.suburbpostcode.configuration;


import com.techsharif.suburbpostcode.entity.Suburb;
import com.techsharif.suburbpostcode.repository.SuburbRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = Suburb.class)
@EnableJpaRepositories(basePackageClasses = SuburbRepository.class)
@EnableJpaAuditing
public class JpaConfig {
}
