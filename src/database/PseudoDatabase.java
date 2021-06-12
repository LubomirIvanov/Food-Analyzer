package database;

import dto.Food;
import dto.FoodDetails;
import dto.FoodInfo;
import java.util.HashMap;
import java.util.Map;

public class PseudoDatabase {
    private static PseudoDatabase instance = new PseudoDatabase();
    private Map<String, FoodInfo> generalFoodInfo;
    private Map<String, FoodDetails> descriptionFoodInfo;
    private Map<String, Food> barcodeToQueryMap;

    public static PseudoDatabase getInstance()
    {
        return PseudoDatabase.instance;
    }
    private PseudoDatabase()
    {
        generalFoodInfo = new HashMap<>();
        descriptionFoodInfo = new HashMap<>();
        barcodeToQueryMap = new HashMap<>();
    }
    public Map<String,FoodInfo> getGeneralFoodInfo()
    {
        return generalFoodInfo;
    }
    public Map<String,FoodDetails> getDescriptionFoodInfo()
    {
        return descriptionFoodInfo;
    }
    public Map<String,Food> getBarcodeToQueryMap()
    {
        return barcodeToQueryMap;
    }

    //For testing purposes
    public void clear()
    {
        generalFoodInfo.clear();
        descriptionFoodInfo.clear();
        barcodeToQueryMap.clear();
    }
    public boolean checkIfExistsInDescriptionMap(String parameters) {
        return getDescriptionFoodInfo().containsKey(parameters);
    }

    public boolean checkIfExistsInGeneralMap(String parameters) {
        return getGeneralFoodInfo().containsKey(parameters);
    }

    public boolean checkIfExistsInBarcodeMap(String parameters) {
        return getBarcodeToQueryMap().containsKey(parameters);
    }
}
