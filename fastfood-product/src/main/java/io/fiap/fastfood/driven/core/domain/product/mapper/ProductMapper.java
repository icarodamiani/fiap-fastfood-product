package io.fiap.fastfood.driven.core.domain.product.mapper;

import io.fiap.fastfood.driven.core.domain.model.Product;
import io.fiap.fastfood.driven.core.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductTypeMapper.class})
public interface ProductMapper {
    ProductEntity entityFromDomain(Product product);

    Product domainFromEntity(ProductEntity productEntity);
}
