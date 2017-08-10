package com.htdf.ma.scheduler.config;

import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hifly.db.Db;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class BasicConfig {
	
	@Value("${driverClass}")
	private String driver;
	
	@Value("${jdbcUrl}")
	private String url;
	
	@Value("${user}")
	private String username;
	
	@Value("${password}")
	private String password;
	
	@Bean
	public ComboPooledDataSource dataSource() throws PropertyVetoException{
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(driver);
		dataSource.setJdbcUrl(url);
		dataSource.setUser(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	@Bean
	public Db connection() throws PropertyVetoException{
		Db.loadSystemDs(dataSource());
		Db db = new Db();
		return db;
	}
}
