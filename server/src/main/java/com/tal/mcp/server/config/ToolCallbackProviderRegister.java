package com.tal.mcp.server.config;

import com.tal.mcp.server.tools.DeepSeekService;
import com.tal.mcp.server.tools.OpenMeteoService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ToolCallbackProviderRegister {

    @Bean
    public ToolCallbackProvider weatherTools(OpenMeteoService weatherService) {
        return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
    }

    @Bean
    public ToolCallbackProvider balanceTools(DeepSeekService deepSeekService) {
        return MethodToolCallbackProvider.builder().toolObjects(deepSeekService).build();
    }

}
