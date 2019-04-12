package com.hk.operators.entities;


import org.apache.commons.math3.ode.ODEIntegrator;

/**
 * Created by kunhe on 12/29/18.
 */
public class OrderItem extends Item {
    private String user;

    public OrderItem()
    {}

    public OrderItem(Item o)
    {
       super(o);
    }

    public int getCatalog() {
        return catalog;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public long getTs() {
        return ts;
    }
    public void setTs(long ts) {
        this.ts = ts;
    }


    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

}

