package com.bing.lan.spring;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 被 {@link MapperScan} 扫描的类会被 {@link MapperScannerConfigurer#postProcessBeanDefinitionRegistry}
 * 定义成 {@link MapperFactoryBean } 并注册到 Spring 容器中，最终通过 {@link MapperFactoryBean#getObject()}
 * 与 mybatis 中的mapper关联起来
 * <p>
 * EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
 * <p>
 * <p>
 * 通过注解 开始事务 看{@link EnableTransactionManagement}上方详细文档
 * http://www.imooc.com/wiki/springbootlesson/transcation.html
 */

@Configuration
@EnableTransactionManagement
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
  public SpringManagedTransactionFactory transactionFactory() {
    // 一定要使用这个
    return new SpringManagedTransactionFactory();
  }

  @Bean
  public PlatformTransactionManager txManager() {
    return new DataSourceTransactionManager(dataSource());
  }
}
