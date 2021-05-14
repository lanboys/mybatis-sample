# mybatis-sample
### mybatis日志
##### 日志配置关键类 org.apache.ibatis.logging.LogFactory
> 系统默认按顺序依次配置，直至配置成功为止，如果需要定制可以在配置文件中配置，见下方

```$java
  // org.apache.ibatis.logging.LogFactory
  static {
    tryImplementation(LogFactory::useSlf4jLogging);
    tryImplementation(LogFactory::useCommonsLogging);
    tryImplementation(LogFactory::useLog4J2Logging);
    tryImplementation(LogFactory::useLog4JLogging);
    tryImplementation(LogFactory::useJdkLogging);// jdk日志一般都会有的，且日志级别一定是INFO及以上，所以sql默认是不会被打印出来的
    tryImplementation(LogFactory::useNoLogging);// 不打印日志
  }
    
  // 系统提供的日志相关的别名 org.apache.ibatis.session.Configuration
  typeAliasRegistry.registerAlias("SLF4J", Slf4jImpl.class);
  typeAliasRegistry.registerAlias("COMMONS_LOGGING", JakartaCommonsLoggingImpl.class);
  typeAliasRegistry.registerAlias("LOG4J", Log4jImpl.class);
  typeAliasRegistry.registerAlias("LOG4J2", Log4j2Impl.class);
  typeAliasRegistry.registerAlias("JDK_LOGGING", Jdk14LoggingImpl.class);
  typeAliasRegistry.registerAlias("STDOUT_LOGGING", StdOutImpl.class);
  typeAliasRegistry.registerAlias("NO_LOGGING", NoLoggingImpl.class);

  // 配置文件
  <configuration>
      <settings>
          <!-- 可通过别名配置日志 或者 配置自定义日志实现 -->
          <!--<setting name="logImpl" value="JDK_LOGGING"/>-->
          <setting name="logImpl" value="STDOUT_LOGGING"/>
      </settings>
  </configuration>
```