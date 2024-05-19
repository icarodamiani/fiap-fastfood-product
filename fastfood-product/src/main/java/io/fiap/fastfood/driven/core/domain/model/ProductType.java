package io.fiap.fastfood.driven.core.domain.model;

public record ProductType(
    Long id,
    String description) {


    public static final class ProductTypeBuilder {
        private Long id;
        private String description;

        private ProductTypeBuilder() {
        }

        public static ProductTypeBuilder builder() {
            return new ProductTypeBuilder();
        }

        public ProductTypeBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ProductTypeBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductType build() {
            return new ProductType(id, description);
        }
    }
}
