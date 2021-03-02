# Spring-cloud-alibaba
## 环境说明
### 版本信息
|groupId|artifactId|version|
|----|----|----|
|org.springframework.cloud|spring-cloud-dependencies|Hoxton.SR9|
|org.springframework.cloud|spring-boot-dependencies|Hoxton.SR9|
|com.alibaba.cloud|spring-cloud-alibaba-dependencies|Hoxton.SR9|

## Nacos
官网地址: https://nacos.io/zh-cn/docs/what-is-nacos.html

### 启动方式
进到Nacos的bin目录，在bin目录下执行命令
#### windows

startup.cmd -m standalone

#### unix

startup -m standalone

### hystrix dashborad
访问地址：http://localhost:8100/actuator/hystrix.stream

添加依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

添加配置
```yaml
management:
  endpoints:
    web:
      exposure:
        include: '*'
```