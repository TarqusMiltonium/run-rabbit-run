package org.example.util;

import java.util.Objects;

public class KeyValuePair<K, V> {
    final K key;
    V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.key, this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (! (obj instanceof KeyValuePair<?,?>)) { return false; }

        KeyValuePair<?,?> kvp = (KeyValuePair<?,?>)obj;
        return (Objects.equals(kvp.getKey(), this.key) && Objects.equals(kvp.getValue(), this.value));
    }

    @Override
    public String toString() {
        return String.format("Key: %s\nValue:%s", this.key.toString(), this.value.toString());
    }
}
