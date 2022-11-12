package com.epam.tamentoring.enums;

import java.util.Random;

public enum ProductName {
    BEER,
    COFFEE,
    TEA,
    WHISKEY,
    JUICE,
    COKE,
    WINE,
    SPARKLING_WATER,
    ENERGY_DRINK,
    MILK,
    CIDER;

    private static final Random PRNG = new Random();

    public static String randomProductName(){
        ProductName[] productNames = values();
        return productNames[PRNG.nextInt(productNames.length)].toString();
    }
}
