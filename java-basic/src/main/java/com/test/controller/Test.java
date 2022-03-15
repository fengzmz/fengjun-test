package com.test.controller;

import com.test.entity.Computer;
import com.test.entity.Food;
import com.test.entity.Goods;

import java.math.BigDecimal;

/**
 * @className Test
 * @description
 * @author fengjun
 * @date 2022年01月28日 上午8:51
 */
public class Test {
    public static void main(String[] args) {
        Goods<String> goods = new Goods<>();
        goods.setName("别克");
        goods.setPrice(9999.9);
        goods.setSort("汽车");

        Computer computer = new Computer();
        computer.setCompany("惠普");
        computer.setName("hp");
        computer.setPrice(88888.8);
        computer.setSort(99);

        Food<BigDecimal> food = new Food<>();
        food.setName("苹果");
        food.setPrice(7.7);
        food.setSort(new BigDecimal("6.54"));
    }



}
