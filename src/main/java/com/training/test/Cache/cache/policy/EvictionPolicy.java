package com.training.test.Cache.cache.policy;

public interface EvictionPolicy<Key> {

    public void keyAccessed(Key key);
    public Key evictKey();
}
