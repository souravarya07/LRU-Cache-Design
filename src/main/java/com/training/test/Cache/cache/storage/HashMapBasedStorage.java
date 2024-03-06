package com.training.test.Cache.cache.storage;

import com.training.test.Cache.cache.exceptions.NotFoundException;
import com.training.test.Cache.cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;
public class HashMapBasedStorage<Key, Value> implements Storage<Key, Value> {

    Map<Key, Value> storage;
    private final Integer capacity;

    public HashMapBasedStorage (Integer capacity) {
        this.capacity = capacity;
        this.storage = new HashMap<>();
    }

    @Override
    public void add(Key key, Value value) {
        if (!isStorageFull()) {
            storage.put(key, value);
        } else {
            throw new StorageFullException("Capacity is full!!");
        }
    }

    @Override
    public void remove(Key key) {
        if (storage.containsKey(key)) {
            storage.remove(key);
        } else {
            throw new NotFoundException(key + " is not found!!");
        }
    }

    @Override
    public Value get(Key key) {
        if (storage.containsKey(key)) {
            return storage.get(key);
        } else {
            throw new NotFoundException( key + " is not present in storage!!");
        }
    }

    private boolean isStorageFull() {
        return storage.size() == capacity;
    }
}
