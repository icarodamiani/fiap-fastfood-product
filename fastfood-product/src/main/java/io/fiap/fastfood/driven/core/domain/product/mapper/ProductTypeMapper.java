package io.fiap.fastfood.driven.core.domain.product.mapper;

import io.fiap.fastfood.driven.core.domain.model.ProductType;
import io.fiap.fastfood.driven.core.entity.ProductTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {
    ProductTypeEntity entityFromDomain(ProductType productType);

    ProductType domainFromEntity(ProductTypeEntity productTypeEntity);
}
