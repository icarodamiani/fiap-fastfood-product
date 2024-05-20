package io.fiap.fastfood.driven.core.domain.product.port.outbound;

import io.fiap.fastfood.driven.core.domain.model.Product;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductPort {

    Mono<Product> createProduct(Product product);

    Mono<Product> updateProduct(String id, String operations);

    Mono<Void> deleteProduct(String id);

    Flux<Product> listProduct(String typeId, Pageable pageable);
}
