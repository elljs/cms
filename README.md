## 介绍

Java，这门由Sun Microsystems（现被Oracle公司收购）于1995年推出的编程语言，至今已走过了数十年的辉煌历程。凭借其强大的工程化能力、成熟的社区支持和良好的国产化适应性，Java依然是软件开发领域的中流砥柱。然而，由于Java历史悠久，市面上存在大量过时和错误的教程，这无疑给初学或其他语言的开发者的学习带来了不小的困扰。因此，本文将为您梳理一条现代Java学习的正确路径，帮助您高效、准确地掌握Java开发技能。

由于本文旨在聚焦现代Java开发的核心技能，因此将不包含JavaSE（Java Standard Edition，即Java标准版）和JVM（Java Virtual Machine，即Java虚拟机）等基础知识。

## 环境搭建

### 安装目标

**JDK**

JDK（Java Development Kit）是Java开发工具包的简称它是Java的核心，包括了Java运行环境（JRE）、Java工具和Java基础的类库。JDK是整个Java开发的核心，它提供了编译、运行和调试Java程序所需的所有工具。

**Maven**

Maven是一个基于POM（Project Object Model，项目对象模型）的项目管理工具，它提供了项目构建和依赖管理两大核心功能。

### 安装步骤

1. 安装 SDKMAN

   SDKMAN 是一个软件开发工具包管理器，用于管理不同版本的 Java SDK 和 Maven。在终端中运行以下命令来安装 SDKMAN：

   ```bash
   curl -s "https://get.sdkman.io" | bash
   ```

   安装完成后，关闭并重新打开终端，或运行以下命令以使 SDKMAN 立即生效：

   ```bash
   source "$HOME/.sdkman/bin/sdkman-init.sh"
   ```

2. 使用 SDKMAN 安装 Java 17(SpringBoot 3.0项目，官方推荐使用 Java 17)：

   安装 Java 17 的最新版本：

   ```bash
   sdk install java 17.0.12-jbr
   ```

3. 验证 Java 安装：

   验证 Java 是否正确安装：

   ```bash
   java -version
   ```

4. 使用 SDKMAN 安装 Maven 3.9.9

   安装特定版本的 Maven：

   ```bash
   sdk install maven 3.9.9
   ```

5. 验证 Maven 安装

   验证 Maven 是否正确安装：

   ```bash
   mvn -version
   ```
  
## 创建 SpringBoot 工程

1. 使用 Spring Initializr 创建项目

   访问 [Spring Initializr](https://start.spring.io/) 网站，选择项目元数据（如 Group, Artifact），依赖项（如 Spring Web）。完成后，下载生成的项目压缩包，并解压到你的项目文件夹中。

2. 或者使用 Maven 命令行创建项目

   如果你更喜欢使用命令行，可以使用以下 Maven 命令来生成一个基本的 SpringBoot 项目：

   安装 maven springboot archetype
   ```bash
   git clone git@github.com:elljs/maven-spring-boot-3-archetype.git 
   cd maven-spring-boot-3-archetype
   mvn install
   ```

   使用 maven archetype 创建项目，需要将 groudId 替换为你的公司或组织的ID 以及将 artifactId 替换为你的项目名称

   ```bash   
   mvn archetype:generate \
      -DgroupId=com.ell \
      -DartifactId=springboot-project \
      -DarchetypeGroupId=com.ell.maven-archetypes \
      -DarchetypeArtifactId=spring-boot-3 \
      -DarchetypeVersion=1.0 \
      -DinteractiveMode=false
   ```

   然后，进入项目目录：

   ```bash
   cd springboot-project
   ```

3. 安装项目依赖

   使用 Maven 安装项目依赖：

   ```bash
   mvn install
   ```

4. 运行 SpringBoot 应用

   使用 Maven 运行 SpringBoot 应用：

   ```bash
   mvn spring-boot:run
   ```

5. 编译项目

   将 Java 源代码编译为字节码：

   ```bash
   mvn compile
   ```

6. 打包项目

   将编译后的字节码打包为一个可执行的 JAR 文件：

   ```bash
   mvn package
   ```

7. 生产模式运行

   使用 Java 命令执行打包后的 JAR 文件：

   ```bash
   java -jar target/springboot-project-1.0-SNAPSHOT.jar
   ```
   
## 配置文件
Spring Boot的配置文件是项目中用于配置各种属性、参数和设置的重要文件。以下是关于Spring Boot配置文件的详细介绍：

1. **命名**：
   - Spring Boot的配置文件通常命名为`application.properties`或`application.yml`。
   - 其中，`.properties`文件使用键值对的形式编写配置，而`.yml`文件则使用YAML语法，具有更高的可读性和层次结构。

2. **位置**：
   - 默认情况下，配置文件位于`src/main/resources`目录下。
   - 也可以在项目打包时，通过配置文件复制插件或环境参数等方式，将配置文件放置在指定的位置。


#### 优先级和加载顺序

1. **优先级**：
   - 如果项目中同时存在`application.properties`和`application.yml`文件，Spring Boot会先加载`application.properties`文件，然后再加载`application.yml`文件，因此`application.yml`中的配置可以覆盖`application.properties`中的相同配置。

2. **加载顺序**：
   - Spring Boot会按照特定的顺序加载配置文件，包括默认的`application.properties`或`application.yml`文件、项目根目录下的`application.properties`或`application.yml`文件、以及通过命令行参数或环境变量指定的配置文件等。

#### 多环境配置

在实际开发中，项目通常会在开发环境、测试环境和生产环境中运行，而这些环境可能需要不同的配置信息。Spring Boot支持多环境配置，可以通过创建多个以`application-{profile}.properties`或`application-{profile}.yml`命名的配置文件来实现。

例如，可以为开发环境创建一个名为`application-dev.properties`或`application-dev.yml`的配置文件，并为测试环境创建一个名为`application-test.properties`或`application-test.yml`的配置文件。然后，在主配置文件`application.properties`或`application.yml`中使用`spring.profiles.active`属性来指定当前激活的环境配置文件。

#### 读取配置文件中的属性

在Spring Boot中，可以通过多种方式读取配置文件中的属性：

1. **使用@Value注解**：
   - 可以将`@Value`注解标注在成员变量上，通过`${key}`的形式来获取配置文件中的属性值。

2. **使用@ConfigurationProperties注解**：
   - 可以将`@ConfigurationProperties`注解标注在实体类上，并将配置文件中以某个前缀开头的属性映射到该类的属性上。这种方式更加适合用于配置复杂的对象或结构。

## 热重载

spring-boot-devtools 是Spring Boot提供的一组开发工具，旨在提高开发效率和调试便利性。它包含了自动重启、实时属性更新、依赖项热替换等实用功能。

1. **自动重启**：
   - 当检测到类路径上的文件发生变化时（如Java源文件、资源文件等），应用会自动重启。

2. **实时属性更新**：
   - 允许开发者在运行时更新应用的配置属性，而无需重启应用。

3. **依赖项热替换**：
   - 支持在运行时替换项目的依赖项，而无需重启整个应用。

要在Spring Boot项目中使用spring-boot-devtools，你需要按照以下步骤进行配置：

1. **添加依赖**：
   - 在项目的`pom.xml`文件中添加spring-boot-devtools的依赖。

    ```xml
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <!-- 表示依赖项在编译阶段不需要，但在运行时是必需的。 -->
            <scope>runtime</scope>
            <!-- 用于标记依赖项是否为可选的。即它不是项目核心功能所必需的，但在某些特定情况下可能会用到。 -->
            <optional>true</optional>
    </dependency>
    ```

2. **配置IDE**：
   - 在某些情况下，你可能需要禁用IDE的自动编译功能，以避免与devtools的自动重启功能发生冲突。

3. **运行应用**：
   - 使用Spring Boot的内置服务器运行应用。
   - 当你在IDE中修改代码并保存时，devtools会自动检测到更改并重启应用。

### 可能遇到的问题

尽管spring-boot-devtools提供了许多有用的功能，但在使用过程中也可能会遇到一些问题：

1. **性能问题**：
   - 在生产环境中使用devtools可能会导致性能下降。因此，请确保在部署到生产环境时禁用devtools。

2. **缓存问题**：
   - devtools会自动禁用某些缓存（如模板引擎缓存），以提高开发效率。但在某些情况下，这可能会导致意外的行为。

3. **类加载问题**：
   - 由于devtools使用两个类加载器（一个用于应用类，另一个用于devtools类），在某些复杂的项目结构中可能会遇到类加载问题。

4. **与其他工具的冲突**：
   - devtools可能会与某些IDE插件或构建工具产生冲突。

## Web服务器

### Tomcat

SpringBoot 在底层使用了 Tomcat 作为其内嵌 Web 服务器之一。这意味着，当你使用 SpringBoot 构建应用程序时，你实际上是在以一种更简洁和自动化的方式使用 Tomcat 的所有功能。SpringBoot 通过其自动配置和 starters，为 Tomcat 提供了额外的抽象层，使得代码更加整洁和易于维护。

下面是一个在Java项目中使用内嵌Tomcat的案例:

首先安装依赖

```xml
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-core</artifactId>
    <version>8.5.100</version>
</dependency>
```

在Java主类的main方法中启动Tomcat
```java
package com.ell.app;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class App {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir("temp");

        tomcat.addContext("", new File(".").getAbsolutePath());

        tomcat.addServlet("", "hello", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                PrintWriter writer = resp.getWriter();
                writer.write("Hello World");
            }
        }).addMapping("/");

        tomcat.start();
        System.out.println("Server is running on http://localhost:8080");
        tomcat.getServer().await();
    }
}
```

使用Maven插件运行该项目

```xml
 <plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>3.0.0</version>
    <configuration>
        <mainClass>com.ell.app.App</mainClass>
    </configuration>
</plugin>
```

```bash
mvn exec:java
```
浏览器访问: [http://localhost:8080](http://localhost:8080) 可以看到输出 `Hello World`

使用过滤器

```java
package com.ell.App;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 转换为HTTP请求和响应以访问特定的方法和属性
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 请求开始时间
        long start = System.currentTimeMillis();

        // 继续执行过滤器链
        chain.doFilter(request, response);

        // 计算响应时间
        long duration = System.currentTimeMillis() - start;

        // 获取响应状态码
        int statusCode = httpResponse.getStatus();

        // 记录日志
        String method = httpRequest.getMethod();
        String url = httpRequest.getRequestURI();
        log(method, url, statusCode, duration);
    }

    @Override
    public void destroy() {
    }

    private void log(String method, String url, int statusCode, long duration) {
        // 构建日志信息
        String logMessage = String.format("%s %s %d %dms", method, url, statusCode, duration);
        System.out.println(logMessage); // 输出到控制台，也可以改为记录到文件或其他日志系统
    }
}
```

要让 Tomcat 能正确加载过滤器，需要为上下文对象设置正确的类加载器

```java
public static void main(String[] args) throws LifecycleException {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);
    tomcat.setBaseDir("temp");

    Context context = tomcat.addContext("", new File(".").getAbsolutePath());
    ClassLoader classLoader = tomcat.getClass().getClassLoader();
    context.setParentClassLoader(classLoader);

    tomcat.addServlet("", "hello", new HttpServlet() {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            PrintWriter writer = resp.getWriter();
            writer.write("Hello World");
        }
    }).addMapping("/");

    FilterDef filterDef = new FilterDef();
    filterDef.setFilterClass(LoggerFilter.class.getName());
    filterDef.setFilterName("loggerFilter");
    context.addFilterDef(filterDef);

    FilterMap filterMap = new FilterMap();
    filterMap.setFilterName("loggerFilter");
    filterMap.addURLPattern("/*");
    context.addFilterMap(filterMap);

    tomcat.start();
    System.out.println("Server is running on http://localhost:8080");
    tomcat.getServer().await();
}
```

再次访问 http://localhost:8080 可以看到浏览器输出 `Hello World` 的同时，终端输出了日志。

### Undertow

Undertow是一个基于Java的高性能Web服务器，由Red Hat公司开发。它是一个轻量级、灵活且可嵌入的Web服务器，提供了异步非阻塞的处理方式和高度可扩展的架构。Undertow支持HTTP/1.1和HTTP/2协议，并提供了对WebSocket的轻量级和高效实现。

-   **Tomcat**：使用传统的阻塞式I/O（BIO）模型，在处理大量并发连接时可能会遇到性能瓶颈。
-   **Undertow**：采用非阻塞式I/O（NIO）模型，能够高效地处理高并发请求，提供更高的吞吐量和响应速度。

在Spring Boot 3中，将默认的Tomcat替换为Undertow:

1. 排除Tomcat依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

2. 增加Undertow依赖
```xml
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-undertow</artifactId>
</dependency>
```

3. 配置Undertow参数
```yml
server:
    # 服务器运行端口
    port: 9999
    undertow:
      threads:
        # I/O线程数
        io: 4
        # 工作线程数
        worker: 20
      # 设置缓冲区大小
      buffer-size: 1024
```

## 日志框架

Springboot3默认的日志框架是Logback，同时采用SLF4J（Simple Logging Facade for Java）作为日志门面。

在项目的 resources 目录下新建一个 logback-spring.xml 用于配置日志框架的参数:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!-- 继承默认配置 -->
    <include resource="org/springframework/boot/logging/logback/defaults.xml" />
    <!-- 控制台日志输出 -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${CONSOLE_LOG_PATTERN}</pattern>
            <charset>utf8</charset>
        </encoder>
    </appender>
    <!-- 文件日志输出 -->
    <appender name="APP_LOG" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
            <charset>utf8</charset>
        </encoder>
        <file>logs/app.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">
            <maxIndex>1</maxIndex>
            <fileNamePattern>logs/app.log.%i</fileNamePattern>
        </rollingPolicy>
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>1MB</MaxFileSize>
        </triggeringPolicy>
    </appender>
    <!-- INFO及以上等级的日志将被同时输出到控制台和文件 -->
    <root level="INFO">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="APP_LOG" />
    </root>
</configuration>
```

通过LoggerFactory使用日志:
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

private static final Logger logger = LoggerFactory.getLogger(Application.class);

logger.info("");
```

## 依赖注入

在Spring Boot中，依赖注入（Dependency Injection, DI）是一种重要的设计模式，它允许一个对象在创建时接收其他对象作为参数，而不是自己创建这些对象。Spring Boot通过注解和配置类简化了依赖注入的过程。以下是Spring Boot中常用的依赖注入注解及其介绍：

1. @Configuration

`@Configuration`注解用于标记一个类作为配置类，这意味着Spring容器会特别处理这个类，用于定义和配置Bean。在配置类中，你可以使用`@Bean`注解来声明Bean的创建方法。

```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```

2. @Component

`@Component`是一个通用的注解，用于标记一个类作为Spring容器中的一个Bean。Spring会自动扫描带有`@Component`注解的类，并将其实例化后注册到Spring容器中。

```java
@Component
public class MyComponent {
    // ...
}
```

3. @Service

`@Service`注解是`@Component`的一个特化，它专门用于标记服务层的组件。使用`@Service`注解可以让代码更加清晰，表明这个类是一个服务组件。

```java
@Service
public class MyService {
    // ...
}
```

4. @RestController

`@RestController`注解是`@Controller`和`@ResponseBody`的组合，它专门用于标记控制层的组件，并且返回的数据会直接作为HTTP响应体返回给客户端。`@RestController`注解的类中的方法通常使用`@GetMapping`、`@PostMapping`等注解来映射HTTP请求。

```java
@RestController
public class MyController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
```

#### 依赖注入方式

在Spring Boot中，依赖注入主要通过以下几种方式实现：

1. **构造器注入**：
   通过构造器将依赖注入到类中。这是推荐的方式，因为它可以确保依赖在对象创建时就被注入，并且避免了字段注入可能带来的问题（如循环依赖）。

   ```java
   @Service
   public class MyService {
       private final MyRepository myRepository;

       @Autowired
       public MyService(MyRepository myRepository) {
           this.myRepository = myRepository;
       }
   }
   ```

2. **Setter注入**：
   通过setter方法将依赖注入到类中。这种方式不如构造器注入常用，但在某些情况下（如可选依赖）可能更合适。

   ```java
   @Service
   public class MyService {
       private MyRepository myRepository;

       @Autowired
       public void setMyRepository(MyRepository myRepository) {
           this.myRepository = myRepository;
       }
   }
   ```

3. **字段注入**（不推荐）：
   直接在字段上使用`@Autowired`注解进行注入。这种方式虽然简单，但可能导致代码难以测试和维护，因此不推荐使用。

   ```java
   @Service
   public class MyService {
       @Autowired
       private MyRepository myRepository;
   }
   ```
   
## 面向切面编程

面向切面编程（Aspect Oriented Programming，AOP）是编程范式的一种，旨在将横切关注点（cross-cutting concerns）与业务逻辑代码分离。横切关注点是指那些跨越应用程序多个模块的功能，如日志记录、权限验证、事务管理等。在AOP中，这些横切关注点被封装成切面（Aspect），并通过切点（Pointcut）与业务逻辑代码进行关联。

AOP的核心理念在于“关注点分离”，它允许开发者在不修改业务逻辑代码的情况下，动态地添加横切关注点。这不仅提高了代码的可维护性和可读性，还减少了代码的冗余和重复。

在Spring框架中，AOP得到了广泛的应用。Spring AOP通过代理机制实现了切面与业务逻辑代码的分离，支持前置通知（Before Advice）、后置通知（After Advice）、环绕通知（Around Advice）等多种通知类型。

### 安装依赖

```xml
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

### 使用AOP

定义一个切面类，用于实现日志记录功能。在切面类中，使用`@Aspect`注解标识这是一个切面，并使用`@Component`注解将其注册为Spring容器中的一个Bean。

```java
package com.ell.cms.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // 定义一个横切点，表示对哪些类的哪些方法进行拦截
    @Pointcut("execution(* com.ell.cms.service..*(..))")
    public void serviceLayer() {
    }

    // 在方法执行前进行日志记录
    @Before("serviceLayer()")
    public void logBefore() {
        logger.info("Before method execution. Logging aspect triggered.");
    }

    // 在方法执行后进行日志记录
    @After("serviceLayer()")
    public void logAfter() {
        logger.info("After method execution. Logging aspect triggered.");
    }
}
```

AOP的应用场景远不止于此，它还可以用于权限验证、事务管理、性能监控等多个方面。

## 数据校验
spring-boot-starter-validation是基于`javax.validation`（现为`jakarta.validation`）和Hibernate Validator实现数据校验库。它为对象属性提供了丰富的校验注解，同时支持自定义校验逻辑。这些注解可以用于前端数据校验，确保传入的请求数据符合规定的格式和要求；也可以用于服务端逻辑校验，确保服务内部的数据符合业务逻辑，以防止数据不一致或异常情况；还可以用于数据层保护，通过校验确保入库的数据是符合规范的，有助于保持数据的完整性和一致性。

安装依赖
```xml
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

常用注解及其功能

1.  **@NotNull**：确保字段不为null。适用类型：所有类型（不能为基础数据类型如int，因为它们不能为null）。
1.  **@Null**：确保字段为null。适用类型：所有类型。
1.  **@NotEmpty**：确保集合、字符串、数组等不为空（不能为空且大小/长度不能为0）。适用类型：字符串、集合、数组等。
1.  **@NotBlank**：确保字符串不为空白（即不能为空，且至少包含一个非空白字符）。适用类型：字符串。
1.  **@Size**：限制集合、数组或字符串的大小或长度在指定范围内。min：最小长度（默认为0）；max：最大长度。适用类型：字符串、集合、数组等。
1.  **@Min和@Max**：限制数值类型的字段值的最小值和最大值。value：允许的最小/最大值。适用类型：数字类型（如int、long、double等）。
1.  **@Positive和@PositiveOrZero**：@Positive确保字段值为正数；@PositiveOrZero确保字段值为非负数（即正数或零）。适用类型：数字类型。
1.  **@Negative和@NegativeOrZero**：@Negative确保字段值为负数；@NegativeOrZero确保字段值为非正数（即负数或零）。适用类型：数字类型。
1.  **@Past和@PastOrPresent**：@Past确保日期在当前日期之前；@PastOrPresent确保日期在当前日期或之前。适用类型：java.util.Date、java.time.LocalDate等日期类型。
1.  **@Future和@FutureOrPresent**：@Future确保日期在当前日期之后；@FutureOrPresent确保日期在当前日期或之后。适用类型：java.util.Date、java.time.LocalDate等日期类型。
1.  **@Pattern**：确保字符串符合指定的正则表达式。regexp：指定的正则表达式；flags：正则表达式的匹配标志（如大小写敏感性）。适用类型：字符串。
1.  **@Email**：确保字符串符合电子邮件格式。regexp：正则表达式，默认符合标准的邮箱格式；flags：正则表达式的匹配标志。适用类型：字符串。
1.  **@Digits**：限制数值字段的整数位和小数位的最大位数。integer：最大整数位数；fraction：最大小数位数。适用类型：数字类型。
1.  **@DecimalMin和@DecimalMax**：限制字段数值的最小值和最大值（包含边界）。value：允许的最小或最大值；inclusive：是否包含边界值，默认为true。适用类型：数字类型。
1.  **@AssertTrue和@AssertFalse**：@AssertTrue确保字段值为true；@AssertFalse确保字段值为false。适用类型：布尔类型。

定义数据传输对象
```java
package com.ell.cms.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDto {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度在6-20之间")
    private String password;

    @Min(value = 0, message = "年龄最小为0")
    @Max(value = 100, message = "年龄最大为100")
    private Integer age;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号码格式错误")
    @NotBlank(message = "手机号不能为空")
    private String phone;
}
```

在控制器中使用
```java
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class ValidationController {
    @PostMapping
    public String createUser(@RequestBody @Valid UserDto user) {
        // 这里的user对象已经经过了校验，如果校验不通过，则会抛出异常
        return "User created successfully!";
    }
}
```

## 异常处理

在Spring Boot 3中，异常过滤器机制通常是通过实现`javax.servlet.Filter`接口或使用Spring提供的`FilterRegistrationBean`来定义过滤器，以拦截并处理请求和响应中的异常。过滤器可以在请求到达控制器之前或响应返回给客户端之后执行特定的逻辑。

异常过滤器的一个常见用途是捕获并处理特定的异常，然后返回一个统一的错误响应给客户端。这有助于保持API的一致性和可维护性。

当spring-boot-starter-validation数据校验失败时，Spring会自动抛出一个`MethodArgumentNotValidException`异常。

要将异常过滤器与spring-boot-starter-validation配合使用：

1.  **定义异常过滤器**：

    创建一个实现`javax.servlet.Filter`接口的类，并在其中定义过滤逻辑。在`doFilter`方法中，捕获并处理`MethodArgumentNotValidException`异常。

2.  **配置过滤器**：

    使用`@Configuration`注解的类来配置你的过滤器，并指定它应该拦截哪些URL模式。

3.  **全局异常处理**：

    除了使用过滤器来处理异常外，Spring Boot还提供了全局异常处理机制，允许通过`@ControllerAdvice`和`@ExceptionHandler`注解来集中处理异常。这种方法通常更灵活，因为它允许根据异常类型返回不同的响应。

```java
package com.ell.cms.aop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 处理所有未捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex, HttpServletRequest request)
            throws Exception {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 0);
        response.put("status", "error");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 处理方法参数校验失败的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 0);
        response.put("status", "error");
        response.put("message", "Validation failed");

        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
```

## 集成常用类库

### Lombok

Lombok 是一个 Java 库，它通过注解的方式自动为 Java 类生成诸如 getter、setter、equals、hashCode、toString 等方法的代码。Lombok 的主要目的是减少样板代码的编写，使 Java 代码更加简洁、易读和可维护。它利用 Java 编译器的注解处理器技术在编译期间扫描和解析使用了 Lombok 注解的源代码，然后生成相应的代码片段，并将其插入到编译器的内部结构中。

安装依赖
```yml
<dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>			
        <version>1.18.36</version>
</dependency>
```

使用Lombok注解
```java
import lombok.Data;
 
@Data
public class User {
    private String name;
    private int age;
}
```

### Hutool

Hutool 是一个 Java 工具包，它提供了丰富的工具类和方法，旨在帮助开发者简化开发工作，减少重复劳动，提升开发效率。

Hutool 由多个模块组成，每个模块都专注于特定的功能，以下是一些核心模块及其功能：

1.  **hutool-core**：核心工具类，包括字符串、数组、日期、集合、Bean 操作等。
1.  **hutool-crypto**：加密解密模块，提供对称、非对称加密解密算法。
1.  **hutool-http**：HTTP 客户端工具，用于发送 HTTP 请求。
1.  **hutool-poi**：操作 Excel 的工具类，支持读写 Excel 文件。
1.  **hutool-db**：数据库操作模块，简化了 JDBC 的使用。
1.  **hutool-captcha**：生成验证码的工具类。

安装依赖
```yml
<dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-all</artifactId>			
        <version>5.8.34</version>
</dependency>
```

使用Console工具
```java
import cn.hutool.core.lang.Console;

Console.log("Hello");
```

### SpringDoc
SpringDoc是一款基于Swagger的开源文档管理工具，专注于生成和管理API文档。

安装依赖
```xml
<dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.7.0</version>
</dependency>
```

创建`SpringdocConfig`配置类
```java
package com.ell.cms.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "jwt", scheme = "bearer", in = SecuritySchemeIn.HEADER, bearerFormat = "JWT")
public class SpringdocConfig {
}
```

使用注解
```java
package com.ell.cms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "授权")
@SecurityRequirement(name = "jwt")
public class AuthController {
    @GetMapping("login")
    @Operation(summary = "登录")
    public SaResult doLogin() {
        // 第1步，先登录上
        StpUtil.login(1L);
        // 第2步，获取 Token 相关参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 第3步，在会话中设置自定义数据
        StpUtil.getSession().set("user", 1L);
        // 第4步，返回给前端
        return SaResult.data(tokenInfo);
    }

    @GetMapping("logout")
    @Operation(summary = "登出")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    @GetMapping("status")
    @Operation(summary = "状态")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }
}
```

访问[http://localhost:9999/swagger-ui/index.html](http://localhost:9999/swagger-ui/index.html)可以看到OpenAPI文档

## 集成中间件
在进行Redis、MySQL、RecketMQ等中间件的集成之前需要准备好本机的环境，下面介绍在macOS上如何快速搭建环境。

### OrbStack

OrbStack是一个针对macOS设计的工具，旨在快速运行Docker容器和Linux虚拟机，同时保持较低的资源占用。以下是关于OrbStack的详细介绍：

1. **功能概述**：

   * 作为一个轻量级、高效的虚拟化解决方案，旨在替代Docker Desktop和Linux虚拟机。
   * 提供快速启动、低资源占用和简单易用的特点。
   * 对苹果芯片的支持非常出色，尤其是针对Apple Silicon系列芯片（如M1、M2、M3、M4等ARM架构的芯片）。
   * 支持涡轮增压网络、流畅的Rosetta模拟、VirtioFS文件共享等优化功能。
     例如：RecketMQ 官方只提供 amd64 平台的镜像，OrbStack通过Rosetta可在Apple Silicon系列芯片的机器上无缝运行该镜像。 

2. **核心特性**：

   * **快速启动**：OrbStack能够在2秒内启动，并优化了网络和磁盘性能。
   * **资源占用低**：具有较低的CPU和磁盘使用率，使用更少的内存，对电池友好。
   * **简单易用**：用户只需一行命令就可以启动Docker容器或Linux虚拟机。
   * **强大功能**：支持无缝运行Docker容器、完整的Linux发行版和Kubernetes。

3. **使用方式**：

   * **安装OrbStack**：用户可以使用Homebrew等包管理器来安装OrbStack。
   * **拉取并运行镜像**：使用docker run等命令来拉取并运行Docker镜像。
   * **管理容器和虚拟机**：用户可以在OrbStack中轻松管理多个Docker容器和Linux虚拟机，包括启动、停止、删除等操作。

### Docker Compose

Docker Compose是一个用于定义和运行复杂Docker应用的工具。以下是关于Docker Compose的详细介绍：

1. **功能概述**：

   * 通过一个配置文件（通常为docker-compose.yml）来管理多个Docker容器。
   * 允许用户轻松地启动、停止和重启应用及其依赖的服务，而无需手动管理每个容器的生命周期。

2. **核心特性**：

   * **定义和运行多容器应用**：用户可以通过配置文件来定义多个容器的运行方式，并一次性启动或停止所有容器。
   * **容器编排**：Docker Compose支持容器的编排，根据容器的依赖关系自动启动和停止容器，确保应用在运行时具有正确的环境。
   * **网络配置**：支持自定义网络配置，使得容器之间的通信更加灵活和可靠。
   * **数据卷管理**：支持数据卷的挂载和管理，便于共享和持久化容器数据。
   * **扩展性**：具有良好的扩展性，用户可以轻松地添加和删除容器以满足应用需求。

3. **使用方式**：

   * **安装Docker Compose**：用户需要在机器上安装Docker Compose，可以从Docker官网下载适用于不同操作系统的安装包，或者使用包管理器进行安装。
   * **编写Compose文件**：创建一个YAML格式的配置文件（如compose.yml），并编写容器配置信息。
   * **运行Compose命令**：在包含配置文件的目录中运行docker compose up等命令来启动应用。可以使用docker compose down命令来停止和删除所有容器。

在项目根目录中创建 docker 文件夹，用于存放docker compose配置文件以及一些初始化脚本

在 `docker` 文件中创建 **compose.yml** 文件
```yml
version: '3.8'
services:
  # MySQL 8.0 service
  mysql:
    image: mysql:8.0
    container_name: mysql
    ports:
      - "3306:3306"
    environment:
      - MYSQL_ROOT_PASSWORD=123456
      - TZ=Asia/Shanghai
    networks:
      - ell
    volumes:
      - mysql-storage:/var/lib/mysql
      - ./init-db.sql:/docker-entrypoint-initdb.d/init-db.sql
  
  # Redis 5.0 service
  redis:
    image: redis:5.0
    container_name: redis
    ports:
      - "6379:6379"
    networks:
      - ell
    command: redis-server --requirepass 123456
  
  # RecketMQ 5.0 service
  namesrv:
    image: apache/rocketmq:5.3.1
    container_name: rmqnamesrv
    ports:
      - 9876:9876
    networks:
      - ell
    command: sh mqnamesrv
  broker:
    image: apache/rocketmq:5.3.1
    container_name: rmqbroker
    ports:
      - 10909:10909
      - 10911:10911
      - 10912:10912
    environment:
      - NAMESRV_ADDR=rmqnamesrv:9876
    depends_on:
      - namesrv
    networks:
      - ell
    command: sh mqbroker
  proxy:
    image: apache/rocketmq:5.3.1
    container_name: rmqproxy
    networks:
      - ell
    depends_on:
      - broker
      - namesrv
    ports:
      - 8080:8080
      - 8081:8081
    restart: on-failure
    environment:
      - NAMESRV_ADDR=rmqnamesrv:9876
    command: sh mqproxy

networks:
  ell:
    driver: bridge

volumes:
  mysql-storage:
```
在`docker`文件中创建 **init-db.sql** 文件用于初始化数据库
```sql
CREATE DATABASE IF NOT EXISTS `ell`;
ALTER DATABASE `ell` CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;
```
最后在`docker`目录中执行 `docker compose up -d` 就可以启动所有服务并让它们在后台运行

### 集成Redis

1. 安装依赖
```xml
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-pool2</artifactId>
</dependency>
```

2. 创建Redis配置类
```java
package com.ell.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RedisConfig {
    @Bean
    public RedisSerializer<Object> redisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 反序列化时候遇到不匹配的属性并不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 序列化时候遇到空对象不抛出异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 反序列化的时候如果是无效子类型,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        // 不使用默认的dateTime进行序列化,
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        // 使用JSR310提供的序列化类,里面包含了大量的JDK8时间序列化类
        objectMapper.registerModule(new JavaTimeModule());
        // 启用反序列化所需的类型信息,在属性中添加@class
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY);
        // 配置null值的序列化器
        GenericJackson2JsonRedisSerializer.registerNullValueSerializer(objectMapper, null);
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory,
            RedisSerializer<Object> redisSerializer) {
        // 创建一个RedisTemplate实例
        RedisTemplate<Object, Object> template = new RedisTemplate<>();

        // 设置Redis连接工厂，这是RedisTemplate与Redis服务器进行通信所必需的
        template.setConnectionFactory(redisConnectionFactory);

        // 设置默认的序列化器，用于将对象序列化为字节数组，以便存储在Redis中
        template.setDefaultSerializer(redisSerializer);

        // 设置值对象的序列化器，与setDefaultSerializer类似，但仅针对值对象
        template.setValueSerializer(redisSerializer);

        // 设置哈希值对象的序列化器，当使用哈希数据结构时，这个序列化器将用于序列化哈希中的值
        template.setHashValueSerializer(redisSerializer);

        // 设置键的序列化器，这里使用StringRedisSerializer来确保键是以UTF-8编码的字符串形式存储的
        template.setKeySerializer(StringRedisSerializer.UTF_8);

        // 设置哈希键的序列化器，与setKeySerializer类似，但仅针对哈希数据结构中的键
        template.setHashKeySerializer(StringRedisSerializer.UTF_8);

        // 调用afterPropertiesSet方法，这个方法是在所有的bean属性被Spring容器设置之后调用的
        // 它用于执行一些初始化工作，比如检查配置是否正确等
        template.afterPropertiesSet();

        // 返回配置好的RedisTemplate实例
        return template;
    }
}
```
3. 添加Redis配置文件
```yml
spring:
  data:
    redis:
      # Redis服务器地址
      host: 127.0.0.1
      # Redis服务器连接端口
      port: 6379
      # Redis服务器密码
      password: 123456
      # 连接超时时间
      timeout: 10s      
      lettuce:
          pool:
            # 连接池最大连接数
            max-active: 200
            # 连接池最大阻塞等待时间（使用负值表示没有限制）
            max-wait: -1ms
            # 连接池中的最大空闲连接
            max-idle: 10
            # 连接池中的最小空闲连接
            min-idle: 0
```

4. 使用RedisTemplate
```java
import org.springframework.data.redis.core.RedisTemplate;

@Autowired
private RedisTemplate<String, String> redisTemplate;

redisTemplate.opsForValue().set("test", "ell");
redisTemplate.opsForValue().get("test");
```

### 缓存
spring-boot-starter-cache是Spring Boot提供的一个用于简化缓存配置的启动器。它提供了一套抽象的缓存接口和注解，使得开发者可以方便地在Spring Boot应用中集成和使用各种缓存技术，而无需关注底层缓存实现的细节。

安装依赖
```xml
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```

创建`CacheConfig`配置类
```java
package com.ell.cms.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory,
            RedisSerializer<Object> redisSerializer) {
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(getRedisCacheConfigurationWithTtl(60, redisSerializer))
                .build();
        return cacheManager;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer minutes,
            RedisSerializer<Object> redisSerializer) {

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .entryTtl(Duration.ofMinutes(minutes));

        return redisCacheConfiguration;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName())
                    .append(':')
                    .append(method.getName());
            if (params.length > 0) {
                sb.append('[');
                for (Object obj : params) {
                    if (obj != null) {
                        sb.append(obj.toString());
                    }
                }
                sb.append(']');
            }
            return sb.toString();
        };
    }
}
```

`@Cacheable`、`@CachePut`和`@CacheEvict`是Spring框架提供的三个缓存相关的注解，它们用于控制方法级别的缓存行为。下面分别介绍这三个注解以及它们的使用方法。

### @Cacheable

**作用**：用于方法上，表示该方法的返回值应该被缓存。如果缓存中已经存在与参数匹配的值，则直接返回缓存中的值，不执行方法体。

**属性**：
- `value` 或 `cacheNames`：指定缓存名称，可以指定多个缓存。
- `key`：指定缓存的键，可以使用SpEL表达式。
- `condition`：指定条件表达式，只有满足条件时才进行缓存。
- `unless`：与`condition`相反，只有不满足条件时才进行缓存。

**使用示例**：
```java
@Cacheable(value = "books", key = "#id")
public Book findBookById(Long id) {
    // 模拟数据库查询
    return bookRepository.findById(id);
}
```
在这个例子中，`findBookById`方法的结果会被缓存到名为"books"的缓存中，键为方法参数`id`的值。

### @CachePut

**作用**：用于方法上，表示该方法的结果应该被缓存，无论方法执行前后，都会执行方法体。

**属性**：
- `value` 或 `cacheNames`：指定缓存名称。
- `key`：指定缓存的键。
- `condition`：指定条件表达式。
- `unless`：指定条件表达式。

**使用示例**：
```java
@CachePut(value = "books", key = "#book.id")
public Book updateBook(Book book) {
    // 更新书籍信息
    return bookRepository.save(book);
}
```
在这个例子中，`updateBook`方法的结果会被缓存到名为"books"的缓存中，键为方法参数`book`对象的`id`属性的值。

### @CacheEvict

**作用**：用于方法上，表示应该从缓存中移除一个或多个条目。

**属性**：
- `value` 或 `cacheNames`：指定缓存名称。
- `key`：指定缓存的键。
- `allEntries`：如果为`true`，则清空整个缓存。
- `beforeInvocation`：指定是否在方法执行前就清除缓存，默认为`false`，即方法执行后清除。
- `condition`：指定条件表达式。
- `unless`：指定条件表达式。

**使用示例**：
```java
@CacheEvict(value = "books", key = "#id")
public void deleteBook(Long id) {
    // 删除书籍
    bookRepository.deleteById(id);
}
```
在这个例子中，`deleteBook`方法执行后，会从名为"books"的缓存中移除键为`id`的条目。


### 授权认证

SpringBoot 3 在授权认证方面提供了多种机制，包括基于Spring Security的完整安全框架，以及更轻量级的第三方库，如Sa-Token。Sa-Token是一个轻量级、无侵入的Java权限认证框架，主要解决：登录认证、权限认证、Session会话、单点登录、OAuth2.0 等一系列功能。

1. 在pom.xml中定义Sa-Token使用的版本号
```xml
<properties>
        <java.version>17</java.version>
        <sa.version>1.39.0</sa.version>
</properties>
```

2. 安装依赖，这里使用Sa-Token JWT风格和Sa-Token Redis存储
```xml
<dependency>
        <groupId>cn.dev33</groupId>
        <artifactId>sa-token-spring-boot3-starter</artifactId>
        <version>${sa.version}</version>
</dependency>
<dependency>
        <groupId>cn.dev33</groupId>
        <artifactId>sa-token-redis-jackson</artifactId>
        <version>${sa.version}</version>
</dependency>
<dependency>
        <groupId>cn.dev33</groupId>
        <artifactId>sa-token-jwt</artifactId>
        <version>${sa.version}</version>
</dependency>
```
3. 新增Sa-Token的配置类
```java
package com.ell.cms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;

@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {

            SaRouter.match("/api/**")
                    .notMatch(

                            "/api/auth/login")
                    .check(r -> StpUtil.checkLogin());

        })).addPathPatterns("/api/**");
    }
}
```

4. 配置Sa-Token
```yml
sa-token:
  # token 名称（同时也是 cookie 名称）
  token-name: Authorization
  # token 前缀
  token-prefix: Bearer
  # token 有效期（单位：秒） 默认30天，-1 代表永久有效
  timeout: 2592000
  # token 最低活跃频率（单位：秒），如果 token 超过此时间没有访问系统就会被冻结，默认-1 代表不限制，永不冻结
  active-timeout: -1
  # 是否允许同一账号多地同时登录 （为 true 时允许一起登录, 为 false 时新登录挤掉旧登录）
  is-concurrent: true
  # 在多人登录同一账号时，是否共用一个 token （为 true 时所有登录共用一个 token, 为 false 时每次登录新建一个 token）
  is-share: true
  # token 风格（默认可取值：uuid、simple-uuid、random-32、random-64、random-128、tik）
  token-style: uuid
  # 是否输出操作日志
  is-log: true
  # jwt秘钥
  jwt-secret-key: 你的秘钥
```

由于使用了Redis作为Sa-Token的数据持久层，所以还需要进行Redis的集成和配置

5. 使用Sa-Token
```java
package com.ell.cms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "授权")
public class AuthController {
    @GetMapping("login")
    @Operation(summary = "登录")
    public SaResult doLogin() {
        // 第1步，先登录上
        StpUtil.login(1L);
        // 第2步，获取 Token 相关参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 第3步，在会话中设置自定义数据
        StpUtil.getSession().set("user", 1L);
        // 第4步，返回给前端
        return SaResult.data(tokenInfo);
    }

    @GetMapping("logout")
    @Operation(summary = "登出")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    @GetMapping("status")
    @Operation(summary = "状态")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }
}
```

根据配置所有以`/api`开头的路由必须经过验证，而`/api/auth/login`路由可以直接访问

使用Sa-Token监听器
```java
package com.ell.cms.listener;

import org.springframework.stereotype.Component;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;

@Component
public class AppSaTokenListener implements SaTokenListener {

    /** 每次登录时触发 */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        System.out.println("---------- 自定义侦听器实现 doLogin");
    }

    /** 每次注销时触发 */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        System.out.println("---------- 自定义侦听器实现 doLogout");
    }

    /** 每次被踢下线时触发 */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        System.out.println("---------- 自定义侦听器实现 doKickout");
    }

    /** 每次被顶下线时触发 */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        System.out.println("---------- 自定义侦听器实现 doReplaced");
    }

    /** 每次被封禁时触发 */
    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {
        System.out.println("---------- 自定义侦听器实现 doDisable");
    }

    /** 每次被解封时触发 */
    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {
        System.out.println("---------- 自定义侦听器实现 doUntieDisable");
    }

    /** 每次二级认证时触发 */
    @Override
    public void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {
        System.out.println("---------- 自定义侦听器实现 doOpenSafe");
    }

    /** 每次退出二级认证时触发 */
    @Override
    public void doCloseSafe(String loginType, String tokenValue, String service) {
        System.out.println("---------- 自定义侦听器实现 doCloseSafe");
    }

    /** 每次创建Session时触发 */
    @Override
    public void doCreateSession(String id) {
        System.out.println("---------- 自定义侦听器实现 doCreateSession");
    }

    /** 每次注销Session时触发 */
    @Override
    public void doLogoutSession(String id) {
        System.out.println("---------- 自定义侦听器实现 doLogoutSession");
    }

    /** 每次Token续期时触发 */
    @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {
        System.out.println("---------- 自定义侦听器实现 doRenewTimeout");
    }
}
```

### 集成Redisson
Redisson是架设在Redis基础上的一个Java驻内存数据网格（In-Memory Data Grid）。它实现了Redis的分布式和响应式特性，为Java开发者提供了更为方便和高级的与Redis交互的方式。Redisson不仅支持Redis的所有基础数据结构，如String、Hash、List、Set、Sorted Set等，还提供了许多分布式的高级功能，如分布式锁、分布式集合、分布式服务、分布式对象等。这些功能使得Redisson在分布式系统中具有广泛的应用场景，如分布式缓存、分布式会话管理、分布式锁等。

安装依赖
```xml
<dependency>
        <groupId>org.redisson</groupId>
        <artifactId>redisson-spring-boot-starter</artifactId>
        <version>3.39.0</version>
</dependency>
```

创建`RedissonConfig`配置类
```java
package com.ell.cms.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redisHost + ":" + redisPort)
                .setPassword(redisPassword);
        return Redisson.create(config);
    }
}
```

使用分布式锁
```java
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LockService {

   @Autowired
   private RedissonClient redissonClient;

   public void doSomethingWithLock() {
       RLock lock = redissonClient.getLock("myLock");
       try {
           // 尝试获取锁，等待时间100ms，锁定时间10秒
           if (lock.tryLock(100, 10, TimeUnit.SECONDS)) {
               try {
                   // 加锁成功，执行业务逻辑
                   System.out.println("锁定成功，正在执行关键任务...");
                   // 模拟任务执行
                   Thread.sleep(5000);
               } finally {
                   // 释放锁
                   lock.unlock();
                   System.out.println("任务完成，已释放锁");
               }
           } else {
               System.out.println("无法获取锁，其他线程正在执行该任务");
           }
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }
}
```

### 集成MySQL

Flyway 是一个数据库迁移工具，它跟踪、管理和应用数据库的版本变更。

安装Maven Flyway插件
```xml
<plugin>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-maven-plugin</artifactId>
        <version>11.0.0</version>
        <configuration>
                <url>jdbc:mysql://mysql.docker.orb.local:3306/ell</url>
                <user>root</user>
                <password>123456</password>
                <locations>classpath:db/migration</locations>
                <baselineOnMigrate>true</baselineOnMigrate>
        </configuration>
        <dependencies>
                <dependency>
                        <groupId>org.flywaydb</groupId>
                        <artifactId>flyway-mysql</artifactId>
                        <version>11.0.0</version>
                </dependency>
        </dependencies>
</plugin>
```

在 `resources` 目录下创建 `db/migration` 目录，并在其中创建数据库迁移脚本，这里根据Flyway的命名约定创建一个`V1__init.sql` 文件来初始化数据库表结构。

```sql
CREATE TABLE IF NOT EXISTS `t_user` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `mobile` VARCHAR(20) UNIQUE COMMENT '手机',
    `nickname` VARCHAR(255) COMMENT '昵称',
    `avatar` VARCHAR(255) COMMENT '头像',
    `balance` DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '余额',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
    `version` BIGINT NOT NULL DEFAULT 0 COMMENT '乐观锁'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';
```

执行迁移可以看到数据库中成功创建了 `t_user` 表
```bash
mvn flyway:migrate
```

### 集成Mybatis Flex

MyBatis-Flex是一个优雅的MyBatis增强框架，它非常轻量且拥有极高的性能与灵活性。使用MyBatis-Flex可以轻松地连接任何数据库，其内置的QueryWrapper功能极大地减少了SQL编写的工作量，并降低了出错的可能性。

MyBatis-Flex的特点可以归纳为以下几点：

1.  **轻量**：除了MyBatis，MyBatis-Flex没有任何第三方依赖或拦截器，其原理是通过SqlProvider的方式实现的。同时，在执行的过程中，没有任何的Sql解析，这使得它具备极高的性能，并且代码易于跟踪调试，把控性高。
2.  **灵活**：MyBatis-Flex支持Entity的增删改查以及分页查询。同时，它提供了Db+Row工具，可以无需实体类对数据库进行增删改查以及分页查询。内置的QueryWrapper可以轻易实现多表查询、连接查询、子查询等常见的SQL场景。
3.  **强大**：MyBatis-Flex支持任意关系型数据库，还可以通过方言持续扩展。同时，它支持复合主键、逻辑删除、乐观锁配置、数据脱敏、数据审计、数据填充等功能。

安装依赖
```xml
<dependency>
        <groupId>com.mybatis-flex</groupId>
        <artifactId>mybatis-flex-spring-boot3-starter</artifactId>
        <version>1.10.2</version>
</dependency>
<dependency>
        <groupId>com.mybatis-flex</groupId>
        <artifactId>mybatis-flex-processor</artifactId>
        <version>1.10.2</version>
        <scope>provided</scope>
</dependency>
<dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
        <version>9.1.0</version>
</dependency>
<dependency>
        <groupId>com.zaxxer</groupId>
        <artifactId>HikariCP</artifactId>
        <version>6.2.1</version>
</dependency>
```

配置数据源
```yml
spring:
  datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://mysql.docker.orb.local:3306/ell
      username: root
      password: 123456
```

创建`MyBatisFlexConfig`配置类
```java
package com.ell.cms.config;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.mybatisflex.core.audit.AuditManager;

@Configuration
public class MyBatisFlexConfig {
    private static final Logger logger = LoggerFactory
            .getLogger("mybatis-flex-sql");

    public MyBatisFlexConfig() {
        // 开启审计功能
        AuditManager.setAuditEnable(true);

        // 设置 SQL 审计收集器
        AuditManager.setMessageCollector(
                auditMessage -> {
                    var sql = ansi().fg(YELLOW).a(auditMessage.getFullSql()).reset().toString();
                    var elapsedTime = auditMessage.getElapsedTime();
                    var elapsedTimeStr = elapsedTime + "ms";
                    var log = """

                            SQL: {} - 耗时: {}
                            """;
                    if (elapsedTime > 500) {
                        elapsedTimeStr = ansi().fg(RED).a(elapsedTimeStr).reset().toString();
                    } else {
                        elapsedTimeStr = ansi().fg(GREEN).a(elapsedTimeStr).reset().toString();
                    }
                    logger.info(log, sql, elapsedTimeStr);
                });
    }
}
```

如果项目中使用了 `spring-boot-devtools` 并开启了自动重启功能，还需要在 `resources/META-INF` 目录中创建 `spring-devtools.properties` 文件，否则会出现类加载失败的错误。

```
restart.include.mapper=/mapper-[\\w-\\.].jar
restart.include.pagehelper=/pagehelper-[\\w-\\.].jar
restart.include.mybatis-flex=/mybatis-flex-[\\w-\\.]+jar
```


定义Mapper文件路径
```java
package com.ell.cms;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ell.cms.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

创建实体类
```java
package com.ell.cms.model;

import java.math.BigDecimal;
import java.util.Date;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import lombok.Data;

@Data
@Table("t_user")
public class User {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private String nickname;
    private String mobile;
    private BigDecimal balance;
    private Date createdAt;
    private Date updatedAt;
    // 逻辑删除字段
    @Column(isLogicDelete = true)
    private Boolean isDeleted;
    // 乐观锁字段
    @Column(version = true, onUpdateValue = "version + 1")
    private Long version;
}
```

创建Mapper文件
```java
package com.ell.cms.mapper;

import com.ell.cms.model.User;
import com.mybatisflex.core.BaseMapper;

public interface UserMapper extends BaseMapper<User> {

}
```

创建Service接口
```java
package com.ell.cms.service;

import com.ell.cms.model.User;
import com.mybatisflex.core.service.IService;

public interface IUserService extends IService<User> {

}
```

创建Service实现
```
package com.ell.cms.service.impl;

import org.springframework.stereotype.Service;

import com.ell.cms.mapper.UserMapper;
import com.ell.cms.model.User;
import com.ell.cms.service.IUserService;
import com.mybatisflex.spring.service.impl.ServiceImpl;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

}
```

### 集成RocketMQ
RocketMQ是一个开源的分布式消息中间件，由阿里巴巴开发并贡献给Apache软件基金会。它主要用于处理高吞吐量、低延迟的消息传递，在现代分布式系统中有着广泛的应用。RocketMQ提供了消息的发布/订阅、消息存储、消息过滤、消息查询、事务消息、延迟消息等功能，并支持多种消息传输协议和消息格式。

安装依赖
```xml
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-spring-boot-starter</artifactId>
    <version>2.3.1</version>
</dependency>
```

配置RocketMQ
```yml
rocketmq:
  name-server: 127.0.0.1:9876
  # 生产者
  producer:
    group: task_group
    # 消息发送超时时间
    send-message-timeout: 3000
    # 消息最大长度4M
    max-message-size: 4096
    # 消息发送失败重试次数
    retry-times-when-send-failed: 3
    # 异步消息发送失败重试次数
    retry-times-when-send-async-failed: 2
  # 消费者
  consumer:
    group: task_group
    # 每次提取的最大消息数
    pull-batch-size: 5
```

创建`RocketConfig`配置类
```java
package com.ell.cms.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketConfig {

    @Value("${rocketmq.name-server}")
    private String nameServer;

    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    @Value("${rocketmq.producer.send-message-timeout}")
    private Integer sendMsgTimeout;

    @Value("${rocketmq.producer.max-message-size}")
    private Integer maxMessageSize;

    @Value("${rocketmq.producer.retry-times-when-send-failed}")
    private Integer retryTimesWhenSendFailed;

    @Value("${rocketmq.producer.retry-times-when-send-async-failed}")
    private Integer retryTimesWhenSendAsyncFailed;

    @Bean
    public RocketMQTemplate rocketMQTemplate() {
        RocketMQTemplate template = new RocketMQTemplate();
        template.setProducer(defaultMqProducer());
        return template;
    }

    @Bean
    public DefaultMQProducer defaultMqProducer() {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr(this.nameServer);
        producer.setProducerGroup(this.producerGroup);
        producer.setSendMsgTimeout(this.sendMsgTimeout);
        producer.setMaxMessageSize(this.maxMessageSize);
        producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        producer.setRetryTimesWhenSendAsyncFailed(this.retryTimesWhenSendAsyncFailed);
        return producer;
    }
}
```

发送消息
```java
@Autowired
private RocketMQTemplate rocketTemplate;

public String sendMsg() {
    try {
        rocketTemplate.convertAndSend("email-topic", "test message");
        System.out.println("消息发送成功");
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "OK";
}
```

接收消息
```java
package com.ell.cms.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "email-topic", consumerGroup = "task-group")
public class EmailListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("接收到的消息: " + message);
    }
}
```

### 集成 Quartz

Quartz是一个完全由Java编写的开源作业调度框架，核心功能是任务调度，即按照预设的时间规则自动执行任务。它包括了Job（任务）、Trigger（触发器）和Scheduler（调度器）三个核心组件。

-   Job：是任务实现，定义了需要执行的具体业务逻辑。
-   Trigger：定义执行时间，指定了任务何时启动，以及任务的执行频率等。
-   Scheduler：负责调度，是Quartz的核心，它根据Trigger的定义来管理Job的执行。

安装依赖
```xml
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-quartz</artifactId>
</dependency>
```

创建`QuartzConfig`配置类
```java
package com.ell.cms.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ell.cms.task.SampleTask;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail sampleTaskDetail() {
        return JobBuilder.newJob(SampleTask.class)
                .withIdentity("sample-task")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sampleTaskTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10) // 每10秒执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(sampleTaskDetail())
                .withIdentity("sample-task-trigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
```

配置Quartz
```yml
spring:
    quartz:
        # 指定作业存储的类型为 JDBC，使用数据库来存储作业和触发器的信息
        job-store-type: jdbc
        jdbc:
          # 初始化模式设置为 never，表示 Quartz 在启动时不会自动创建或更新数据库表结构
          initialize-schema: never
        properties:
          org:
            quartz:
              # 作业存储配置
              jobStore:
                # 指定数据源的名称为 quartzDS
                dataSource: quartzDS
                # 指定作业存储的类为 JobStoreTX，这是 JDBC 作业存储的默认实现
                class: org.quartz.impl.jdbcjobstore.JobStoreTX
                # 指定 JDBC 代理类为 StdJDBCDelegate，这是与数据库交互的标准代理类
                driverDelegateClass: org.quartz.impl.jdbcjobstore.StdJDBCDelegate
                tablePrefix: QRTZ_
              # 数据源配置
              dataSource:
                quartzDS:
                  provider: hikaricp
                  driver: com.mysql.cj.jdbc.Driver
                  URL: jdbc:mysql://mysql.docker.orb.local:3306/quartz
                  user: root
                  password: 123456
```

创建任务
```java
package com.ell.cms.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class SampleTask implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Executing Sample Task at: " + context.getFireTime());
    }
}
```
在`docker`目录中创建`init-quartz.sql`脚本来初始化Quartz的数据库和表结构

```sql
CREATE DATABASE IF NOT EXISTS `quartz`;
ALTER DATABASE `quartz` CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

USE `quartz`;

DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;
DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;
DROP TABLE IF EXISTS QRTZ_LOCKS;
DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_SIMPROP_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;
DROP TABLE IF EXISTS QRTZ_CALENDARS;

CREATE TABLE QRTZ_JOB_DETAILS(
SCHED_NAME VARCHAR(120) NOT NULL,
JOB_NAME VARCHAR(190) NOT NULL,
JOB_GROUP VARCHAR(190) NOT NULL,
DESCRIPTION VARCHAR(250) NULL,
JOB_CLASS_NAME VARCHAR(250) NOT NULL,
IS_DURABLE VARCHAR(1) NOT NULL,
IS_NONCONCURRENT VARCHAR(1) NOT NULL,
IS_UPDATE_DATA VARCHAR(1) NOT NULL,
REQUESTS_RECOVERY VARCHAR(1) NOT NULL,
JOB_DATA BLOB NULL,
PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
JOB_NAME VARCHAR(190) NOT NULL,
JOB_GROUP VARCHAR(190) NOT NULL,
DESCRIPTION VARCHAR(250) NULL,
NEXT_FIRE_TIME BIGINT(13) NULL,
PREV_FIRE_TIME BIGINT(13) NULL,
PRIORITY INTEGER NULL,
TRIGGER_STATE VARCHAR(16) NOT NULL,
TRIGGER_TYPE VARCHAR(8) NOT NULL,
START_TIME BIGINT(13) NOT NULL,
END_TIME BIGINT(13) NULL,
CALENDAR_NAME VARCHAR(190) NULL,
MISFIRE_INSTR SMALLINT(2) NULL,
JOB_DATA BLOB NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
REFERENCES QRTZ_JOB_DETAILS(SCHED_NAME,JOB_NAME,JOB_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_SIMPLE_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
REPEAT_COUNT BIGINT(7) NOT NULL,
REPEAT_INTERVAL BIGINT(12) NOT NULL,
TIMES_TRIGGERED BIGINT(10) NOT NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_CRON_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
CRON_EXPRESSION VARCHAR(120) NOT NULL,
TIME_ZONE_ID VARCHAR(80),
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_SIMPROP_TRIGGERS
  (
    SCHED_NAME VARCHAR(120) NOT NULL,
    TRIGGER_NAME VARCHAR(190) NOT NULL,
    TRIGGER_GROUP VARCHAR(190) NOT NULL,
    STR_PROP_1 VARCHAR(512) NULL,
    STR_PROP_2 VARCHAR(512) NULL,
    STR_PROP_3 VARCHAR(512) NULL,
    INT_PROP_1 INT NULL,
    INT_PROP_2 INT NULL,
    LONG_PROP_1 BIGINT NULL,
    LONG_PROP_2 BIGINT NULL,
    DEC_PROP_1 NUMERIC(13,4) NULL,
    DEC_PROP_2 NUMERIC(13,4) NULL,
    BOOL_PROP_1 VARCHAR(1) NULL,
    BOOL_PROP_2 VARCHAR(1) NULL,
    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
    FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
    REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_BLOB_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
BLOB_DATA BLOB NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
INDEX (SCHED_NAME,TRIGGER_NAME, TRIGGER_GROUP),
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_CALENDARS (
SCHED_NAME VARCHAR(120) NOT NULL,
CALENDAR_NAME VARCHAR(190) NOT NULL,
CALENDAR BLOB NOT NULL,
PRIMARY KEY (SCHED_NAME,CALENDAR_NAME))
ENGINE=InnoDB;

CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS (
SCHED_NAME VARCHAR(120) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP))
ENGINE=InnoDB;

CREATE TABLE QRTZ_FIRED_TRIGGERS (
SCHED_NAME VARCHAR(120) NOT NULL,
ENTRY_ID VARCHAR(95) NOT NULL,
TRIGGER_NAME VARCHAR(190) NOT NULL,
TRIGGER_GROUP VARCHAR(190) NOT NULL,
INSTANCE_NAME VARCHAR(190) NOT NULL,
FIRED_TIME BIGINT(13) NOT NULL,
SCHED_TIME BIGINT(13) NOT NULL,
PRIORITY INTEGER NOT NULL,
STATE VARCHAR(16) NOT NULL,
JOB_NAME VARCHAR(190) NULL,
JOB_GROUP VARCHAR(190) NULL,
IS_NONCONCURRENT VARCHAR(1) NULL,
REQUESTS_RECOVERY VARCHAR(1) NULL,
PRIMARY KEY (SCHED_NAME,ENTRY_ID))
ENGINE=InnoDB;

CREATE TABLE QRTZ_SCHEDULER_STATE (
SCHED_NAME VARCHAR(120) NOT NULL,
INSTANCE_NAME VARCHAR(190) NOT NULL,
LAST_CHECKIN_TIME BIGINT(13) NOT NULL,
CHECKIN_INTERVAL BIGINT(13) NOT NULL,
PRIMARY KEY (SCHED_NAME,INSTANCE_NAME))
ENGINE=InnoDB;

CREATE TABLE QRTZ_LOCKS (
SCHED_NAME VARCHAR(120) NOT NULL,
LOCK_NAME VARCHAR(40) NOT NULL,
PRIMARY KEY (SCHED_NAME,LOCK_NAME))
ENGINE=InnoDB;

CREATE INDEX IDX_QRTZ_J_REQ_RECOVERY ON QRTZ_JOB_DETAILS(SCHED_NAME,REQUESTS_RECOVERY);
CREATE INDEX IDX_QRTZ_J_GRP ON QRTZ_JOB_DETAILS(SCHED_NAME,JOB_GROUP);

CREATE INDEX IDX_QRTZ_T_J ON QRTZ_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_T_JG ON QRTZ_TRIGGERS(SCHED_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_T_C ON QRTZ_TRIGGERS(SCHED_NAME,CALENDAR_NAME);
CREATE INDEX IDX_QRTZ_T_G ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);
CREATE INDEX IDX_QRTZ_T_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_N_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_N_G_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_NEXT_FIRE_TIME ON QRTZ_TRIGGERS(SCHED_NAME,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_ST ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE_GRP ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE);

CREATE INDEX IDX_QRTZ_FT_TRIG_INST_NAME ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME);
CREATE INDEX IDX_QRTZ_FT_INST_JOB_REQ_RCVRY ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY);
CREATE INDEX IDX_QRTZ_FT_J_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_FT_JG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_FT_T_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP);
CREATE INDEX IDX_QRTZ_FT_TG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);

commit;
```

修改 `docker/compose.yml` 挂载 `init-quartz.sql`

```yml
# MySQL 8.0 service
mysql:
image: mysql:8.0
container_name: mysql
ports:
  - "3306:3306"
environment:
  # 设置root用户的密码
  - MYSQL_ROOT_PASSWORD=123456
  # 设置时区
  - TZ=Asia/Shanghai
networks:
  - ell
volumes:
  - mysql-storage:/var/lib/mysql
  # 初始化数据库的脚本
  - ./init-db.sql:/docker-entrypoint-initdb.d/init-db.sql
  # 初始化Quartz数据库的脚本
  - ./init-quartz.sql:/docker-entrypoint-initdb.d/init-quartz.sql
```

## 源码地址

[https://github.com/elljs/cms](https://github.com/elljs/cms)

## 总结

本文详细介绍了现代Java开发的正确路径，包括环境搭建、Spring Boot工程创建、配置文件、热重载、Web服务器、日志框架、依赖注入、面向切面编程、数据校验、异常处理、集成常用类库、中间件集成等方面的内容。