package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties("app")
public class ApplicationProperties {
    private HttpHeader httpHeader;

    public ApplicationProperties.HttpHeader getHttpHeader() {
        return httpHeader;
    }

    public void setHttpHeader(ApplicationProperties.HttpHeader httpHeader) {
        this.httpHeader = httpHeader;
    }

    @Override
    public String toString() {
        return "applicationProperties{" +
                httpHeader.toString() +
                '}';
    }

    public static class HttpHeader {
        private String vSolvKey;

        @Override
        public String toString() {
            return "httpHeader{" +
                    "vSolvKey=" + this.vSolvKey +
                    '}';
        }

        public String getvSolvKey() {
            return vSolvKey;
        }

        public void setvSolvKey(String vSolvKey) {
            this.vSolvKey = vSolvKey;
        }
    }
}
