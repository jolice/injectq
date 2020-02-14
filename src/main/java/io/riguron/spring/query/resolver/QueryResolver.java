package io.riguron.spring.query.resolver;

import io.riguron.spring.query.EnableInjectQuery;
import io.riguron.spring.query.SqlQuery;
import io.riguron.spring.query.exception.QueryReadException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class QueryResolver {

    private final Map<String, String> cache = new ConcurrentHashMap<>();
    private String path = "";
    private String extension = "sql";

    public String resolve(SqlQuery query) {
        return resolve(query.value(), Charset.forName(query.charset()));
    }

    void clearCache() {
        this.cache.clear();
    }

    private String resolve(String query, Charset charset) {
        return cache.computeIfAbsent(query, s -> {
            try {
                return StreamUtils.copyToString(new ClassPathResource(String.format("%s/%s.%s", path, query, extension)).getInputStream(), charset);
            } catch (IOException e) {
                throw new QueryReadException(e);
            }
        });
    }

    public void configure(EnableInjectQuery configuration) {
        this.path = configuration.path();
        this.extension = configuration.extension();
    }
}
