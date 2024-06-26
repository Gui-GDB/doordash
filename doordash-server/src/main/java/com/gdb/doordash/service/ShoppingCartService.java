package com.gdb.doordash.service;


import com.gdb.doordash.dto.ShoppingCartDTO;
import com.gdb.doordash.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    /**
     * 添加购物车
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 查看购物车
     */
    List<ShoppingCart> showShoppingCart();

    /**
     * 清空购物车商品
     */
    void cleanShoppingCart();

    /**
     * 删除购物车中一个商品
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}