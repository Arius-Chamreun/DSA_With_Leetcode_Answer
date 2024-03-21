/**
 * @author arashxr
 * @email ariuschamreun15@gmail.com
 * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
 */
package com.Arius.myNetwork.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.List;
import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
public class CorsConfig {
  private static final String X_REQUEST_WITH = "X-Request-With";

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource urlCorsConfig = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfig = new CorsConfiguration();

        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedOrigins(List.of("http://localhost:5173"));

        corsConfig.setAllowedHeaders(List.of(ORIGIN,ACCESS_CONTROL_ALLOW_ORIGIN,CONTENT_TYPE,
                ACCEPT,AUTHORIZATION,X_REQUEST_WITH,ACCESS_CONTROL_REQUEST_HEADERS,ACCESS_CONTROL_ALLOW_CREDENTIALS));
        corsConfig.setExposedHeaders(List.of(ORIGIN,ACCESS_CONTROL_ALLOW_ORIGIN,CONTENT_TYPE,
                ACCEPT,AUTHORIZATION,X_REQUEST_WITH,ACCESS_CONTROL_REQUEST_HEADERS,ACCESS_CONTROL_ALLOW_CREDENTIALS));

        corsConfig.setAllowedMethods(List.of(GET.name(),POST.name(),PATCH.name(), DELETE.name(),PUT.name()));

        urlCorsConfig.registerCorsConfiguration("/**", corsConfig);

        return new CorsFilter(urlCorsConfig);
    }
}
