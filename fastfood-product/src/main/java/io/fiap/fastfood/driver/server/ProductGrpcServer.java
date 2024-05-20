package io.fiap.fastfood.driver.server;


import com.google.type.Decimal;
import io.fiap.fastfood.FindAllProductByTypeRequest;
import io.fiap.fastfood.FindAllProductRequest;
import io.fiap.fastfood.ProductResponse;
import io.fiap.fastfood.ProductServiceGrpc;
import io.fiap.fastfood.ProductTypeResponse;
import io.fiap.fastfood.SaveProductRequest;
import io.fiap.fastfood.UpdateProductRequest;
import io.fiap.fastfood.driven.core.domain.model.Product;
import io.fiap.fastfood.driven.core.domain.model.ProductType;
import io.fiap.fastfood.driven.core.service.ProductService;
import io.grpc.stub.StreamObserver;
import java.math.BigDecimal;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

@GrpcService
public class ProductGrpcServer extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductService service;

    private final GrpcStatusConverter statusConverter;

    @Autowired
    public ProductGrpcServer(ProductService service, GrpcStatusConverter statusConverter) {
        this.service = service;
        this.statusConverter = statusConverter;
    }

    @Override
    public void saveProduct(SaveProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        service.create(Product.ProductBuilder.builder()
                .withTypeId(request.getTypeId())
                .withAmount(request.getAmount())
                .withDescription(request.getDescription())
                .withPrice(new BigDecimal(request.getPrice().getValue()))
                .withType(ProductType.ProductTypeBuilder.builder()
                    .withId(request.getType().getId())
                    .withDescription(request.getType().getDescription())
                    .build())
                .build())
            .doOnError(throwable -> responseObserver.onError(statusConverter.toGrpcException(throwable)))
            .map(product ->
                ProductResponse.newBuilder()
                    .setId(product.id())
                    .build()
            )
            .map(response -> {
                responseObserver.onNext(response);
                responseObserver.onCompleted();
                return response;
            })
            .subscribe();
    }

    @Override
    public void updateProduct(UpdateProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        service.update(request.getId(), request.getOperations())
            .doOnError(throwable -> responseObserver.onError(statusConverter.toGrpcException(throwable)))
            .map(customer ->
                ProductResponse.newBuilder()
                    .setId(customer.id())
                    .build()
            )
            .map(response -> {
                responseObserver.onNext(response);
                responseObserver.onCompleted();
                return response;
            })
            .subscribe();
    }

    @Override
    public void findAllProduct(FindAllProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        service.findAll(Pageable.unpaged())
            .doOnError(throwable -> responseObserver.onError(statusConverter.toGrpcException(throwable)))
            .map(product ->
                ProductResponse.newBuilder()
                    .setId(product.id())
                    .setAmount(product.amount())
                    .setPrice(toDecimal(product.price()))
                    .setDescription(product.description())
                    .setTypeId(product.typeId())
                    .setType(ProductTypeResponse.newBuilder()
                        .setId(product.type().id())
                        .setDescription(product.type().description())
                        .build())
                    .build()
            )
            .map(response -> {
                responseObserver.onNext(response);
                return response;
            })
            .doOnComplete(responseObserver::onCompleted)
            .subscribe();
    }

    @Override
    public void findAllProductByType(FindAllProductByTypeRequest request, StreamObserver<ProductResponse> responseObserver) {
        service.findByType(request.getTypeId(), Pageable.unpaged())
            .doOnError(throwable -> responseObserver.onError(statusConverter.toGrpcException(throwable)))
            .map(product ->
                ProductResponse.newBuilder()
                    .setId(product.id())
                    .setAmount(product.amount())
                    .setPrice(toDecimal(product.price()))
                    .setDescription(product.description())
                    .setTypeId(product.typeId())
                    .setType(ProductTypeResponse.newBuilder()
                        .setId(product.type().id())
                        .setDescription(product.type().description())
                        .build())
                    .build()
            )
            .map(response -> {
                responseObserver.onNext(response);
                return response;
            })
            .doOnComplete(responseObserver::onCompleted)
            .subscribe();
    }

    private static Decimal toDecimal(BigDecimal value) {
        return Decimal.newBuilder().setValue(value.toString()).build();
    }
}