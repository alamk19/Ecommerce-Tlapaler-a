package com.curso.ecommerce.config;

import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * <h2>GeoLocationConfig</h2>
 */
@Slf4j
@Configuration
public class GeoLocationConfig {

    private static DatabaseReader reader = null;
    private final ResourceLoader resourceLoader;

    public GeoLocationConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public DatabaseReader databaseReader() {
        try {

            Resource resource = resourceLoader.getResource("classpath:maxmind/GeoLite2-City.mmdb");
            InputStream dbAsStream = resource.getInputStream();

            
            // Initialize the reader
           return setReader(new DatabaseReader
                    .Builder(dbAsStream)
                    .fileMode(Reader.FileMode.MEMORY)
                    .build());

        } catch (IOException | NullPointerException e) {
            return null;
        }
    }

	public static DatabaseReader getReader() {
		return reader;
	}

	public static DatabaseReader setReader(DatabaseReader reader) {
		GeoLocationConfig.reader = reader;
		return reader;
	}
}
