package com.epam.tamentoring.tests;

import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnitTests extends BaseUnitTest{

    @Test
    void checkAddProductToShoppingCartTest() {
        UserAccount user = new UserAccount("Mike", "Lebovsky", "24/08/91", getNewShoppingCart());

        Product product = getNewProduct();

        user.getShoppingCart().addProductToCart(product);

        Assertions.assertEquals(1, user.getShoppingCart().getProducts().size());
    }

    @Test
    void checkAddProductToShoppingCartByIdTest(){
        UserAccount user = new UserAccount("Mike", "Lebovsky", "24/08/91", getNewShoppingCart());

        Product product = getNewProduct();
        int expectedProductId = product.getId();

        user.getShoppingCart().addProductToCart(product);

        Assertions.assertEquals(product, user.getShoppingCart().getProductById(expectedProductId));
    }

    @Test
    void checkAddSameProductToShoppingCart(){
        ShoppingCart shoppingCart = getNewShoppingCart();

        Product product = getNewProduct();

        System.out.println(product);

        shoppingCart.addProductToCart(product);
        shoppingCart.addProductToCart(product);

        Assertions.assertEquals(1, shoppingCart.getProducts().size());
        Assertions.assertEquals(product.getQuantity(),shoppingCart.getProductById(product.getId()).getQuantity());
    }

    @Test
    void removeProductFromShoppingCartTest(){
        int initialProductQuantity = 3;

        //creating user
        UserAccount user = new UserAccount(
                "Mike",
                "Lebovsky",
                "24/08/91",
                getNewShoppingCartWithProductList(initialProductQuantity));

        //adding product to delete
        Product productToDelete = getNewProduct();
        user.getShoppingCart().addProductToCart(productToDelete);

        //check if productToDelete is added
        Assertions.assertEquals(initialProductQuantity + 1, user.getShoppingCart().getProducts().size());

        //delete product
        user.getShoppingCart().removeProductFromCart(productToDelete);

        //Check if product id deleted
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
           user.getShoppingCart().getProductById(productToDelete.getId());
        });
    }

    @Test
    void getTotalPriceOfTheShoppingCartTest(){
        //creating products
        Product product1 = getNewProduct();
        Product product2 = getNewProduct();

        //expected total cost
        double expectedTotalCost =
                product1.getPrice() * product1.getQuantity() +
                product2.getPrice() * product2.getQuantity();

        //creating shopping cart
        ShoppingCart shoppingCart = getNewShoppingCart();
        shoppingCart.addProductToCart(product1);
        shoppingCart.addProductToCart(product2);

        //assert total price of the shopping cart
        Assertions.assertEquals(expectedTotalCost, shoppingCart.getCartTotalPrice());

    }

}
