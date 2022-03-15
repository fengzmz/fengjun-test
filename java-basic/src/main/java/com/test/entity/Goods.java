package com.test.entity;

import lombok.Data;

/**
 * @className
 * @description
 * @author fengjun
 * @date 2022年01月28日 上午8:48
 */

@Data
public class Goods<T> {
    private String name;
    private Double price;
    private T sort;
}
