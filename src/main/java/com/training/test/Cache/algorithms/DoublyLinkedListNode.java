package com.training.test.Cache.algorithms;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;

@Data
public class DoublyLinkedListNode<E> {
    DoublyLinkedListNode<E> next;
    DoublyLinkedListNode<E> prev;
    E element;

    public DoublyLinkedListNode(E element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }

}
