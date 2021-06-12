package dto;

public class FoodDetails {

    private String description;
    private String ingredients;
    private LabelNutrients labelNutrients;
    private String gtinUpc;

    @Override
    public String toString() {
        return String.format("Description: %s" + System.lineSeparator() +
                "Ingredients: %s" + System.lineSeparator(), description, ingredients) +
                labelNutrients.toString();
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public LabelNutrients getLabelNutrients() {
        return labelNutrients;
    }

    public String getGtinUpc() {
        return gtinUpc;
    }
}
