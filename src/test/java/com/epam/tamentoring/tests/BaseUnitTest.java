package com.epam.tamentoring.tests;

import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.enums.ProductName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseUnitTest {

    private static final Logger logger = LogManager.getLogger(BaseUnitTest.class);

    public static Product getNewProduct(){
        Random random = new Random();
        Product product = new Product();
        product.setId(random.nextInt(1000));
        product.setName(ProductName.randomProductName());
        product.setPrice(random.nextInt(200));
        product.setQuantity(random.nextInt(5));
        logger.info("New Product: " + product);
        return product;
    }

    public static List<Product> getNewProductList(int productQuantity){
        List<Product> products = new ArrayList<>();
        for(int i = 0; i < productQuantity; i++){
            products.add(getNewProduct());
        }
        return products;
    }

    public static List<Product> getNewRandomProductList(){
        Random random = new Random();
        List<Product> products = new ArrayList<>();
        for(int i = 0; i < random.nextInt(10); i++){
            products.add(getNewProduct());
        }
        return products;
    }

    public static ShoppingCart getNewShoppingCartWithProductList(int productQuantity){
        return new ShoppingCart(getNewProductList(productQuantity));
    }

    public static ShoppingCart getNewShoppingCartWithRandomProductList(){
        return new ShoppingCart(getNewRandomProductList());
    }

    public static ShoppingCart getNewShoppingCart(){
        return new ShoppingCart(new ArrayList<>());
    }
 }
