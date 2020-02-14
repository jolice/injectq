package io.riguron.spring.query.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CacheRelease {

    private final QueryResolver queryResolver;

    @EventListener(ApplicationReadyEvent.class)
    public void clearCache() {
        queryResolver.clearCache();
    }
}