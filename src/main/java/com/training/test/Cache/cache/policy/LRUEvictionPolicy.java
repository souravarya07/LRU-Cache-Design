package com.training.test.Cache.cache.policy;

import com.training.test.Cache.algorithms.DoublyLinkedList;
import com.training.test.Cache.algorithms.DoublyLinkedListNode;
import com.training.test.Cache.cache.exceptions.InvalidElementException;
import com.training.test.Cache.cache.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key>{
    private DoublyLinkedList<Key> dll;
    private Map<Key, DoublyLinkedListNode<Key>> mapper;

    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkedList<>();
        this.mapper = new HashMap<>();
    }

    @Override
    public void keyAccessed(Key key) {
        if (mapper.containsKey(key)) {
            dll.detachNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        } else {
            DoublyLinkedListNode<Key> newNode = dll.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }

    @Override
    public Key evictKey() {
        DoublyLinkedListNode firstNode = dll.getFirstNode();
        if (firstNode != null) {
            dll.detachNode(firstNode);
            return (Key) firstNode.getElement();
        } else {
            return null;
        }
    }
}
