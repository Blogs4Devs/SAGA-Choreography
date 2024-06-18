package productservice.demo.Config;

import com.dtos.demo.Events.OrderEvent;
import com.dtos.demo.Events.ProductEvent;
import com.dtos.demo.Events.ProductStockState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import productservice.demo.Entities.Product;
import productservice.demo.Repositories.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;


@Configuration
public class OrderEventResponderConfig {

    @Autowired
    private ProductRepository productRepository;

    @Bean
    public Function<Flux<OrderEvent>, Flux<ProductEvent>> orderEventProcessor(){

        return orderEventFlux -> orderEventFlux.flatMap(this::productStockCheck);
    }

    private Mono<ProductEvent> productStockCheck(OrderEvent orderEvent){
        Product product = productRepository.findById(orderEvent.getProdId()).get();
        ProductStockState stockAvailability = (product.getStock() >= orderEvent.getProdqnt())
                                            ? ProductStockState.AVAILABLE : ProductStockState.OUT_OF_STOCK;
        if(stockAvailability.equals(ProductStockState.AVAILABLE)){
            product.setStock(product.getStock() - orderEvent.getProdqnt());
            productRepository.save(product);
        }

        ProductEvent productEvent = new ProductEvent(
                                            orderEvent.getOrderId(),
                                            orderEvent.getProdId(),
                                            orderEvent.getProdqnt(),
                                            stockAvailability);
        return Mono.fromSupplier(() -> productEvent);
    }

}
