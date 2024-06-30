package com.example.text_processor;

import java.util.*;

public class DataCollection {
    private ArrayList<String> arrayList;
    private Map<String, String> map;
    private Set<String  > set;
    final CollectionType type;

    public enum CollectionType {
        ARRAYLIST,
        SET,
        MAP
    }

    public DataCollection(CollectionType type) {
        this.type = type;
        if(type == CollectionType.ARRAYLIST) {
            this.arrayList = new ArrayList<>();
            return;
        }else if(type == CollectionType.SET){
            this.set = new HashSet<>();
            return;
        }else if(type == CollectionType.MAP){
            this.map = new HashMap<>();
            return;
        }
    }

    public void addItem(String item) {
        if (type == CollectionType.ARRAYLIST) {
            this.arrayList.add(item);
        } else if (type == CollectionType.SET) {
            this.set.add(item);
        }
    }

    public void addItem(String key, String value) {
        if (type == CollectionType.MAP) {
            this.map.put(key, value);
        } else {
            throw new UnsupportedOperationException("This method is not supported for ARRAYLIST or SET type");
        }
    }

    public Map<String, String> getMap() {
        return this.map;
    }

    public void updateItem(int index, String newItem) {
        if (type == CollectionType.ARRAYLIST) {
            this.arrayList.set(index, newItem);
        } else if (type == CollectionType.SET) {
            List<String> list = new ArrayList<>(this.set);
            list.set(index, newItem);
            this.set = new HashSet<>(list);
        } else {
            throw new UnsupportedOperationException("This method is not supported for MAP type");
        }
    }

    public void updateItem(String key, String newValue) {
        if (type == CollectionType.MAP) {
            this.map.put(key, newValue);
        } else {
            throw new UnsupportedOperationException("This method is not supported for ARRAYLIST or SET type");
        }
    }

    public void removeItem(int index) {
        if (type == CollectionType.ARRAYLIST) {
            this.arrayList.remove(index);
        } else if (type == CollectionType.SET) {
            List<String> list = new ArrayList<>(this.set);
            list.remove(index);
            this.set = new HashSet<>(list);
        } else {
            throw new UnsupportedOperationException("This method is not supported for MAP type");
        }
    }

    public void removeItem(String key) {
        if (type == CollectionType.MAP) {
            this.map.remove(key);
        } else {
            throw new UnsupportedOperationException("This method is not supported for ARRAYLIST or SET type");
        }
    }

    @Override
    public String toString() {
        if (type == CollectionType.ARRAYLIST) {
            return "ArrayList: " + arrayList.toString();
        } else if (type == CollectionType.SET) {
            return "Set: " + set.toString();
        } else if (type == CollectionType.MAP) {
            return "Map: " + map.toString();
        } else {
            return "Unknown collection type";
        }
    }

    public void clear() {
        if (type == CollectionType.ARRAYLIST) {
            this.arrayList.clear();
        } else if (type == CollectionType.SET) {
            this.set.clear();
        } else if (type == CollectionType.MAP) {
            this.map.clear();
        } else {
            throw new UnsupportedOperationException("Unknown collection type");
        }
    }

    public int size() {
        if (type == CollectionType.ARRAYLIST) {
            return this.arrayList.size();
        } else if (type == CollectionType.SET) {
            return this.set.size();
        } else if (type == CollectionType.MAP) {
            return this.map.size();
        } else {
            throw new UnsupportedOperationException("Unknown collection type");
        }
    }
}
