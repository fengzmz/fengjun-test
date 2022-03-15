package com.test.entity;

import lombok.Data;

/**
 * @className Computer
 * @description
 * @author fengjun
 * @date 2022年01月28日 上午8:56
 */
@Data
public class Computer extends Goods<Integer>{ // Computer 不是泛型类
    private String company;
}
