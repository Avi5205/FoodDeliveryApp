package in.kodder.service;

import in.kodder.entity.IngredientsCategory;
import in.kodder.entity.IngredientsItem;

import java.util.List;

public interface IngredientsService {

    public IngredientsCategory createIngredientsCategory(String name, Long restaurantId) throws Exception;

    public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception;

    public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception;

    public IngredientsItem createIngredientsItem(Long restaurantId, String name, Long categoryId) throws Exception;

    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) throws Exception;

    public IngredientsItem updateStock(Long id) throws Exception;
}
