package com.ithotel.util;


import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;

public class BusinessLogicTest {

    @Test
    public void checkUserBalanceForPayingBillTest() {
        boolean result = BusinessLogic.checkUserBalanceForPayingBill(new BigDecimal(1000), new BigDecimal(100));

        Assert.assertTrue(result);
    }

    @Test
    public void calsulateTotalCostTest(){
        BigDecimal price = new BigDecimal(10);
        Date check_in = Date.valueOf("2021-12-03");
        Date check_out = Date.valueOf("2021-12-06");

        BigDecimal result = BusinessLogic.calsulateTotalCost(price, check_in, check_out);

        BigDecimal expected = new BigDecimal(30);

        Assert.assertEquals(result, expected);
    }

}