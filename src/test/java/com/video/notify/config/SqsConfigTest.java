package com.video.notify.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SqsConfigTest {

    private SqsConfig config;

    @BeforeEach
    void setUp() {
        config = new SqsConfig();
        ReflectionTestUtils.setField(config, "accessKey", "fake-access-key");
        ReflectionTestUtils.setField(config, "secretKey", "fake-secret-key");
        ReflectionTestUtils.setField(config, "region", "us-east-1");
    }

    @Test
    void testSqsAsyncClientCreation() {
        SqsAsyncClient client = config.sqsAsyncClient();
        assertNotNull(client);
    }
}
