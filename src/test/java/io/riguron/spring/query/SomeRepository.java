package io.riguron.spring.query;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class SomeRepository {

    @Getter
    @SqlQuery("field")
    private String fieldQuery;

    @Getter
    private String setterQuery;

    @Getter
    private String constructorQuery;

    public SomeRepository(@SqlQuery("constructor") String constructorQuery) {
        this.constructorQuery = constructorQuery;
    }

    @SqlQuery("setter")
    public void setterQuery(String query) {
        this.setterQuery = query;
    }

}
