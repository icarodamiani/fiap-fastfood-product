package io.fiap.fastfood.driven.core.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public record ProductTypeEntity(
    @Field
    String id,
    @Field
    String description) {


    public static final class ProductTypeEntityBuilder {
        private String id;
        private String description;

        private ProductTypeEntityBuilder() {
        }

        public static ProductTypeEntityBuilder builder() {
            return new ProductTypeEntityBuilder();
        }

        public ProductTypeEntityBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public ProductTypeEntityBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductTypeEntity build() {
            return new ProductTypeEntity(id, description);
        }
    }
}
