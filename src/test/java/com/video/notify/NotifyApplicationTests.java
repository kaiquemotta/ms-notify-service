package com.video.notify;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import static org.mockito.Mockito.mock;

@SpringBootTest
@ActiveProfiles("test")
class NotifyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Configuration
    static class MockConfig {
        @Bean
        public SqsAsyncClient sqsAsyncClient() {
            return mock(SqsAsyncClient.class);
        }
    }
}
