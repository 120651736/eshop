package com.weeked.eshop.inventory.dao;

/**
 * RedisDAO
 * 2020-04-22
 */
public interface RedisDAO {

    void set(String key, String value);

    String get(String key);

}
