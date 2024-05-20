package io.fiap.fastfood.driven.core.service;

import io.fiap.fastfood.driven.core.domain.model.Product;
import io.fiap.fastfood.driven.core.domain.product.port.inbound.ProductUseCase;
import io.fiap.fastfood.driven.core.domain.product.port.outbound.ProductPort;
import io.fiap.fastfood.driven.core.exception.BadRequestException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService implements ProductUseCase {

    private final ProductPort productPort;


    public ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    @Override
    public Mono<Product> create(Product product) {
        return Mono.just(product)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BadRequestException())))
                .flatMap(productPort::createProduct);
    }

    @Override
    public Flux<Product> findAll(Pageable pageable) {
        return productPort.listProduct(null, pageable);
    }

    @Override
    public Flux<Product> findByType(String typeId, Pageable pageable) {
        return productPort.listProduct(typeId, pageable);
    }

    @Override
    public Mono<Void> delete(String id) {
        return Mono.just(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BadRequestException())))
                .flatMap(productPort::deleteProduct);
    }

    @Override
    public Mono<Product> update(String id, String operations) {
        return productPort.updateProduct(id, operations);
    }

}
