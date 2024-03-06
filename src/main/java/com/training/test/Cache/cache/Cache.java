package com.training.test.Cache.cache;

import com.training.test.Cache.cache.exceptions.NotFoundException;
import com.training.test.Cache.cache.exceptions.StorageFullException;
import com.training.test.Cache.cache.policy.EvictionPolicy;
import com.training.test.Cache.cache.storage.Storage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cache<Key, Value> {
    private final Storage<Key, Value> storage;
    private final EvictionPolicy<Key> evictionPolicy;

    public Cache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public void put(Key key, Value value) {
        try {
            this.storage.add(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch (StorageFullException storageFullException) {
            log.info("Storage is full, now try to evict ");
            Key keyToRemove = evictionPolicy.evictKey();
            if (keyToRemove == null) {
                throw new RuntimeException("Unexpected exception occurred!!");
            }
            this.storage.remove(keyToRemove);
            log.info("Space created by evicting item : {}", keyToRemove);
            put(key, value); // Recursive call to put input key value pair
        }
    }

    public Value get(Key key) {
        try {
            Value value = this.storage.get(key);
            this.evictionPolicy.keyAccessed(key);
            return value;
        } catch (NotFoundException notFoundException) {
            log.error("Key : {}, not found in storage", key);
            return null;
        }
    }

}
