package dto;

public class LabelNutrients {
    private Nutrient calories;
    private Nutrient protein;
    private Nutrient fiber;
    private Nutrient carbohydrates;
    private Nutrient fat;

    public LabelNutrients() {
    }

    @Override
    public String toString() {

        return String.format("Calories: %s" + System.lineSeparator() +
                        "Protein: %s" + System.lineSeparator() +
                        "Fiber: %s" + System.lineSeparator() +
                        "Carbohydrates: %s" + System.lineSeparator() +
                        "Fat: %s" + System.lineSeparator(),
                calories.getValue(), protein.getValue(),
                fiber.getValue(), carbohydrates.getValue(), fat.getValue());
    }

    public Nutrient getCalories() {
        return calories;
    }

    public Nutrient getProtein() {
        return protein;
    }

    public Nutrient getFiber() {
        return fiber;
    }

    public Nutrient getCarbohydrates() {
        return carbohydrates;
    }

    public Nutrient getFat() {
        return fat;
    }
}
