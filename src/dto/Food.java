package dto;

public class Food {
    private String fdcId;
    private String description;
    private String dataType;
    private String gtinUpc;
    private String ingredients;

    @Override
    public String toString() {
        return String.format("FdcId: %s" + System.lineSeparator() +
                        "Description: %s" + System.lineSeparator() +
                        "DataType: %s" + System.lineSeparator() +
                        "GtinUpc: %s" + System.lineSeparator() +
                        "Ingredients: %s" + System.lineSeparator(),
                fdcId, description, dataType, gtinUpc, ingredients);
    }

    public String getFdcId() {
        return fdcId;
    }

    public String getDescription() {
        return description;
    }

    public String getDataType() {
        return dataType;
    }

    public String getGtinUpc() {
        return gtinUpc;
    }

    public String getIngredients() {
        return ingredients;
    }

}
