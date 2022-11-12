package com.epam.tamentoring.tests;

import com.epam.tamentoring.bo.DiscountUtility;
import com.epam.tamentoring.bo.OrderService;
import com.epam.tamentoring.bo.UserAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UnitMockTest extends BaseUnitTest{

    @Mock
    private DiscountUtility discountUtility;
    private AutoCloseable closeable;
    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void checkOrderServiceTest(){
        UserAccount user = new UserAccount(
                "John",
                "Smith",
                "1990/10/10",
                getNewShoppingCartWithRandomProductList()
        );

        Mockito.when(discountUtility.calculateDiscount(user)).thenReturn(3.0);

        double actualCartTotalPrice = orderService.getOrderPrice(user);

        double expectedCartTotalPrice = user.getShoppingCart().getCartTotalPrice() - 3.0;

        Assertions.assertEquals(actualCartTotalPrice, expectedCartTotalPrice);

        Mockito.verify(discountUtility, Mockito.times(1)).calculateDiscount(user);

        Mockito.verifyNoMoreInteractions(discountUtility);
    }
}
