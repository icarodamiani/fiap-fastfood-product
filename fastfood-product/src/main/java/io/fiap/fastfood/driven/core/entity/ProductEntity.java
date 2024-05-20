package io.fiap.fastfood.driven.core.entity;

import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("product")
public record ProductEntity(
    @Id
    String id,
    @Field
    String typeId,
    @Field
    String description,
    @Field
    BigDecimal price,
    @Field
    Integer amount,
    @Field
    ProductTypeEntity type) {


    public static final class ProductEntityBuilder {
        private String id;
        private String typeId;
        private String description;
        private BigDecimal price;
        private Integer amount;
        private ProductTypeEntity type;

        private ProductEntityBuilder() {
        }

        public static ProductEntityBuilder builder() {
            return new ProductEntityBuilder();
        }

        public ProductEntityBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public ProductEntityBuilder withTypeId(String typeId) {
            this.typeId = typeId;
            return this;
        }

        public ProductEntityBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductEntityBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductEntityBuilder withAmount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public ProductEntityBuilder withType(ProductTypeEntity type) {
            this.type = type;
            return this;
        }

        public ProductEntity build() {
            return new ProductEntity(id, typeId, description, price, amount, type);
        }
    }
}
