package com.tal.mcp.server;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SSETest {

    private static String host;
    private static int port;
    private McpSyncClient mcpClient;
    private static Process startedProcess;

    @BeforeAll
    public static void init() throws URISyntaxException, IOException {

//        // find jar file
//        Path targetPath = Paths.get(resource.toURI()).getParent().getParent();
//
//        File files = targetPath.toFile();
//        File[] files1 = files.listFiles(file -> (file.getName().endsWith(".jar")));

        // start the jar if not running.
//        try (Socket ignored = new Socket(host, port)) {
//            System.out.println("port " + port + " at host " + "localhost" + " is open。");
//        } catch (IOException e) {
//            // Check if not started.
//            Assertions.assertNotNull(files1, "Not validate directory");
//            Assertions.assertNotEquals(0, files1.length, "Can not find the generated jar file under [target] directory");
//            Assertions.assertEquals(1, files1.length, "Find more then one jar file under [target] directory");
//
//            String jarFilePath = files1[0].getAbsolutePath();
//
//            System.out.println("port " + port + " at host " + "localhost" + "  is close or unreachable。opening...");
//            ProcessBuilder process = new ProcessBuilder("java", "-jar", jarFilePath);
//            startedProcess = process.start();
//            System.out.println("service opened.");
//        }

        // make sure program started
//        Assertions.assertDoesNotThrow(() -> TimeUnit.SECONDS.sleep(5));

    }

    @AfterAll
    public static void destroy() {
        if (startedProcess != null) {
            startedProcess.destroy();
        }
    }

    @BeforeEach
    public void initClient() {
        mcpClient = McpClient.sync(new HttpClientSseClientTransport("http://" + "localhost" + ":" + 8080)).build();
    }

    @AfterEach
    public void close() {
        if (mcpClient != null) {
            mcpClient.closeGracefully();
        }
    }

    @Test
    public void testWeatherMCP() {
        var weather = quickCall("getWeatherForecastByLocation", Map.of("latitude", "0","longitude", "118.78"));

        List<McpSchema.Content> contentList = weather.content();
        String res = easyTextContent(contentList);

        System.out.println(res);

    }

    @Test
    public void banlanceMCP() {
        var balance = quickCall("getBalance", Map.of());

        List<McpSchema.Content> contentList = balance.content();
        String res = easyTextContent(contentList);
        System.out.println(res);
    }


    private McpSchema.CallToolResult quickCall(String name, Map<String, Object> arguments) {
        return mcpClient.callTool(new McpSchema.CallToolRequest(name,arguments));
    }

    private String easyTextContent(List<McpSchema.Content> contentList) {
        assert contentList != null && contentList.size() == 1;
        McpSchema.TextContent textContent = (McpSchema.TextContent) contentList.get(0);
        return textContent.text();
    }
}
