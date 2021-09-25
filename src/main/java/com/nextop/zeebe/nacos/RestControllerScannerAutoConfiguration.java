package com.nextop.zeebe.nacos;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.discovery.NacosDiscoveryAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jeffrey
 */

@Configuration
@AutoConfigureBefore(NacosDiscoveryAutoConfiguration.class)
public class RestControllerScannerAutoConfiguration {

    @Bean
    @ConditionalOnProperty(value = {"nextop.zeebe.nacos.metadata.enabled"}, matchIfMissing = true)
    public NacosDiscoveryProperties discoveryProperties(ApplicationContext applicationcontext) {
        NacosDiscoveryProperties properties = new NacosDiscoveryProperties();
        RestControllerScanner scanner = new RestControllerScanner(applicationcontext, properties);
        scanner.scan();
        properties.getMetadata().put("nextop.zeebe.nacos.metadata.enabled", "true");
        return properties;
    }
}
