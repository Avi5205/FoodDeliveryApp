package in.kodder.request;

import in.kodder.entity.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;
    private Address deliveryAddress;

}
