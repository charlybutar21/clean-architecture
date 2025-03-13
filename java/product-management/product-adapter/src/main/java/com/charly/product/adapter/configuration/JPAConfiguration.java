package com.charly.product.adapter.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.charly.product.adapter.repository")
@EntityScan(basePackages = "com.charly.product.entity.model")
public class JPAConfiguration {}
