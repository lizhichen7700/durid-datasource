package com.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * CommandLineRunner spring-boot启动后要执行的操作
 */
@SpringBootApplication
@Slf4j
public class DruidDatasourceApplication implements CommandLineRunner{

	/**
	 * 通过DataSourceAutoConfiguration配置
     */
	@Autowired
	private DataSource dataSource;

	/**
	 * 通过JdbcTemplateAutoConfiguration配置
     */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 通过DataSourceTransactionManagerAutoConfiguration配置
     */
	@Autowired
	private DataSourceTransactionManager dataSourceTransactionManager;

	public static void main(String[] args) {
		SpringApplication.run(DruidDatasourceApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		log.info("dataSource={}",dataSource);
		showConnection();
		showData();
	}

	private void showData() {
		jdbcTemplate.queryForList("SELECT * FROM currency")
				.forEach(row -> log.info(row.toString()));
	}

	private void showConnection() throws SQLException {
		Connection conn = dataSource.getConnection();
		log.info("conn={}",conn.toString());
		conn.close();
	}
}

