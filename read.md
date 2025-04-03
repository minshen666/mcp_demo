# Spring AI MCP 演示项目

## 项目简介

这是一个基于Spring AI的Model Context Protocol (MCP) 演示项目，展示了如何构建AI模型服务和客户端应用。该项目实现了一个简单的问答系统，能够回答关于天气和余额查询等问题。

## 系统架构

项目由两个主要模块组成：

1. **Server**: MCP服务器，提供AI工具和服务
2. **Client**: MCP客户端，连接到服务器并使用其提供的工具

## 功能特点

- 基于Spring AI和MCP协议实现
- 服务器提供多种工具服务，包括天气查询、空气质量查询和余额查询
- 客户端通过SSE(Server-Sent Events)连接到服务器
- REST API接口支持流式响应
- 支持自然语言处理和多种查询

## 技术栈

- Java 21(得是17+)
- Spring Boot 3.4.x
- Spring AI
- Model Context Protocol (MCP)
- SSE (Server-Sent Events)
- Reactive Programming (Reactor)

## 快速开始

### 环境要求

- JDK 21或更高版本
- Maven 3.6或更高版本

### 构建项目

```bash
mvn clean install -DskipTests
```

### 启动服务器

```bash
cd server
java -jar target/server-0.0.1-SNAPSHOT.jar
```

服务器默认在端口8080上运行，并通过`/sse`端点提供SSE服务。

### 启动客户端

```bash
cd client
java -jar target/client-0.0.1-SNAPSHOT.jar
```

客户端默认在端口8081上运行，并通过`/chat/stream/chat`端点提供流式对话服务。

## API接口

### 客户端API

- **GET /chat/stream/chat**: 流式对话接口
  - 响应格式: 文本流 (text/event-stream)
  - 默认查询: "今天北京天气如何，北京的纬度39.9，经度116.4;我的余额还有多少"
  - 示例访问: `curl http://localhost:8081/chat/stream/chat`

## 配置说明

### 服务器配置 (application.yml)

```yaml
server:
  port: 8080
spring:
  ai:
    mcp:
      server:
        name: my-server
        version: 0.0.1
```

### 客户端配置 (application.yml)

```yaml
server:
  port: 8081
spring:
  application:
    name: mcp
  main:
    web-application-type: servlet
  ai:
    mcp:
      client:
        sse:
          connections:
            server1: # 服务器名称
              url: http://localhost:8080/sse
```

## 项目结构
mcp_demo/
├── client/ # 客户端模块
│ ├── src/
│ │ └── main/
│ │ ├── java/ # Java代码
│ │ └── resources/ # 配置文件
│ └── pom.xml # 客户端Maven配置
├── server/ # 服务器模块
│ ├── src/
│ │ └── main/
│ │ ├── java/ # Java代码
│ │ └── resources/ # 配置文件
│ └── pom.xml # 服务器Maven配置
└── pom.xml # 项目根Maven配置
