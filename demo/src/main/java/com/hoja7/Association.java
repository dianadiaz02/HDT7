package com.hoja7;

import java.util.Map;

public class Association<K,V>
extends java.lang.Object
implements Map.Entry<K,V>, Comparable<Association> {

    K key;
    V value;

    public Association(K key, V value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public int compareTo(Association o) {
        // TODO Auto-generated method stub
        return key.toString().compareToIgnoreCase(o.key.toString());
    }

    @Override
    public K getKey() {
        // TODO Auto-generated method stub
        return key;
    }

    @Override
    public V getValue() {
        // TODO Auto-generated method stub
        return value;
    }

    @Override
    public V setValue(V value) {
        return this.value = value;
    }
    
}
