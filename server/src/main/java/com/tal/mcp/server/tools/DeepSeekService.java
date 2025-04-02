package com.tal.mcp.server.tools;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeepSeekService {
    @Tool(name = "balance",description = "获取余额")
    public String getBalance() {
        String url="https://api.deepseek.com/user/balance";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.setBearerAuth("sk-6c1c9086b20c45afa707120144050f5d");
        HttpEntity<String> request = new HttpEntity<>("",headers);
        try {
            ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.GET, request, JSONObject.class);
            JSONObject body = exchange.getBody();
            if (body != null) {
                return body.toString();
            }
        }catch (Exception e) {
            return "获取余额失败";
        }
        return "获取余额失败";
    }
}
