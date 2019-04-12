package com.hk.operators.entities;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeutils.CompositeType;
import org.apache.flink.api.common.typeutils.TypeSerializer;

import java.util.List;

/**
 * Created by kunhe on 12/29/18.
 */
public class Item {
    protected int catalog;
    protected String name;
    protected float price;
    protected long ts;

    public Item()
    {}


    public Item(Item o)
    {
        this.setCatalog(o.catalog);
        this.setName(o.name);
        this.setPrice(o.price);
        this.setTs(o.ts);
    }

    public int getCatalog() {
        return catalog;
    }
    public void setCatalog(int catalog) {
        this.catalog = catalog;
    }

    public String getName() {
        return name;
    }
    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public long getTs() {
        return ts;
    }
    public void setTs(long ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "Item{" +
                "catalog=" + catalog +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", ts=" + ts +
                '}';
    }
}
