package io.fiap.fastfood.driven.repository;

import io.fiap.fastfood.driven.core.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, String> {

    Flux<ProductEntity> findByTypeId(String typeId, Pageable pageable);

    Flux<ProductEntity> findByIdNotNull(Pageable pageable);

}
