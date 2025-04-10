package com.krishna.translation_demo.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="azure")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AzureProperties {
    private String url;
    private String key;
    private String location;

}
