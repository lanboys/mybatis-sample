package com.bing.lan.spring;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * 被 {@link MapperScan} 扫描的类会被 {@link MapperScannerConfigurer#postProcessBeanDefinitionRegistry}
 * 定义成 {@link MapperFactoryBean } 并注册到 Spring 容器中，最终通过 {@link MapperFactoryBean#getObject()}
 * 与 mybatis 中的mapper关联起来
 * <p>
 * EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
 */

@Configuration
@MapperScan("com.bing.lan.mybatis.mapper")
public class MybatisSpringConfig {

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource());
    factoryBean.setTransactionFactory(transactionFactory());
    // 配置文件中的environment配置不起作用，所以上面的数据源和事务工厂是需要自己定义的
    factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
    return factoryBean.getObject();
  }

  @Bean
  public DataSource dataSource() {
    return new UnpooledDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://14.18.43.48:3306/jdbc",
        "root", "KHwl20170731!@#");
  }

  @Bean
  public JdbcTransactionFactory transactionFactory() {
    return new JdbcTransactionFactory();
  }
}