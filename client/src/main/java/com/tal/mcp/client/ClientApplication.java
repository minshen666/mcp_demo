package com.tal.mcp.client;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;

import com.alibaba.nacos.api.naming.pojo.healthcheck.impl.Http;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org. springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication(exclude = {
        org.springframework.ai.autoconfigure.mcp.client.SseHttpClientTransportAutoConfiguration.class
})
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
    // 直接硬编码中文问题，避免配置文件编码问题
    // @Value("${ai.user.input}")
    // private String userInput;
//    private String userInput = "先告诉我今天北京天气怎么样";
//
//    @Bean
//    public CommandLineRunner predefinedQuestions(ChatClient.Builder chatClientBuilder,
//                                                 ToolCallbackProvider tools,
//                                                 ConfigurableApplicationContext context,
//                                                 DashScopeChatModel chatModel) {
//        return args -> {
////            var chatClient = chatClientBuilder
////                        .defaultTools(tools)
////                        .build();
////                System.out.println("\n>>> 问题: " + userInput);
//////                System.out.println("\n>>> 助手回答: " + chatClient.prompt(userInput).stream().content());
//////                context.close();
//
//            var chatClient = ChatClient.builder(chatModel)
//                    // 实现 Chat Memory 的 Advisor
//                    // 在使用 Chat Memory 时，需要指定对话 ID，以便 Spring AI 处理上下文。
////                    .defaultAdvisors(
////                            new MessageChatMemoryAdvisor(new InMemoryChatMemory())
////                            )
//                    // 实现 Logger 的 Advisor
////                    .defaultAdvisors(
////                            new SimpleLoggerAdvisor()
////                    )
//                    // 设置 ChatClient 中 ChatModel 的 Options 参数
////                    .defaultOptions(
////                            DashScopeChatOptions.builder()
////                                    .withTopP(0.7)
////                                    .build()
////                    )
//                    .defaultTools(tools)
//                    .build();
//            String content = chatClient.prompt("今天北京天气如何，我的余额还有多少").call().content();
//            System.out.println(content);
//        };

