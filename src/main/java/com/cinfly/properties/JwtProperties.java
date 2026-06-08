package com.cinfly.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
   /* secret: cinfly
    expiration: 86400000  # 24小时（毫秒）
    header: Authorization
    prefix: "Bearer*/
    private String secret;
    private  Long expiration;
    private String header;
    private String prefix;

}
