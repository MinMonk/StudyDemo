# spring-cloud-demo
该工程为spring-cloud的一个学习demo，主要使用的技术Eureka、Ribbon、Hystrix、Zuul、Feign
- Eureka: 注册中心
- Ribbon: 用于客户端负载均衡
- Hystrix: 用于服务熔断、降级、限流
- Zuul: 微服务网关
- Feign: 声明式的WebService客户端，让微服务之间的调用更简单

## Module简介
- eureka-server3000: Eureka集群的机器之一，端口为3000
- eureka-server3001: Eureka集群的机器之一，端口为3001
- eureka-server3002: Eureka集群的机器之一，端口为3002
- order: 微服务集群（模拟【订单】微服务），端口为6000
- order1: 微服务集群（模拟【订单】微服务），端口为6001
- power: 微服务集群（模拟【权限】微服务），端口为7000
- power1: 微服务集群（模拟【权限】微服务），端口为7001
- power2: 微服务集群（模拟【权限】微服务），端口为7002
- user: 微服务集群（模拟【用户】微服务），端口为5000，同时他也是这个工程下的客户端角色，调用power/order等微服务。**该工程下有Feign、Ribbon、Hystrix的使用案例**
- zuul: Zuul对外暴露的地址，端口为8000，可以理解为Zuul集群的一个路由地址，客户端通过调用这个地址路由到zuul集群，zuul集群再根据路由配置路由到真实的服务地址，完成调用
- zuul-server8001: Zuul服务端集群的机器之一，端口为8001
- zuul-server8002: Zuul服务端集群的机器之一，端口为8001