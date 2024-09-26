package in.kodder.request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
    private Long cartItemId;
    public int quantity;
}
