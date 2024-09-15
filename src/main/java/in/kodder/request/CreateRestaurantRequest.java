package in.kodder.request;

import in.kodder.entity.Address;
import in.kodder.entity.ContactInformation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {

    // Consider adding validation for ID if it's auto-generated
    // @NotNull
    private Long id;

    @NotNull(message = "Restaurant name cannot be null or empty")
    @Size(min = 3, max = 50, message = "Restaurant name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Restaurant description cannot be blank")
    private String description;

    @NotBlank(message = "Cuisine type cannot be blank")
    private String cuisineType;

    // Assuming Address and ContactInformation already have validation

    private Address address;

    private ContactInformation contactInformation;

    @NotBlank(message = "Opening hours cannot be blank")
    private String openingHours;

    // Consider adding validation for image URLs or formats
    private List<String> images;
}

//@Data
//public class CreateRestaurantRequest {
//    private Long id;
//    private String name;
//    private String description;
//    private String cuisineType;
//    private Address address;
//    private ContactInformation contactInformation;
//    private String openingHours;
//    private List<String> images;
//}
