package com.example.plant_journal.config;

//public class EnvConfigTest {
//    public static void main(String[] args) {
//        System.out.println("AWS_ACCESS_KEY_ID: " + EnvConfig.getEnv("AWS_ACCESS_KEY_ID"));
//        System.out.println("AWS_SECRET_ACCESS_KEY: " + EnvConfig.getEnv("AWS_SECRET_ACCESS_KEY"));
//        System.out.println("AWS_REGION: " + EnvConfig.getEnv("AWS_REGION"));
//        System.out.println("AWS_S3_BUCKET: " + EnvConfig.getEnv("AWS_S3_BUCKET"));
//    }
//}

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class EnvConfigTest {
    @Test
    void testEnvVariables() {
        assertNotNull(EnvConfig.getEnv("AWS_ACCESS_KEY_ID"), "AWS_ACCESS_KEY_ID が null です！");
        assertNotNull(EnvConfig.getEnv("AWS_SECRET_ACCESS_KEY"), "AWS_SECRET_ACCESS_KEY が null です！");
        assertNotNull(EnvConfig.getEnv("AWS_REGION"), "AWS_REGION が null です！");
        assertNotNull(EnvConfig.getEnv("AWS_S3_BUCKET"), "AWS_S3_BUCKET が null です！");
    }
}