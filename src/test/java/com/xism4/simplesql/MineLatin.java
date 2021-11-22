package com.xism4.simplesql;


import com.xism4.simplesql.server.Server;

public final class MineLatin extends Server {

    public MineLatin(String host, String username, String password, String schema) {
        super(host, username, password, schema);
    }

    protected MineLatin() {
        super();
    }

    @Override
    public String getName() {
        return "MineLatin";
    }

    @Override
    public String getVendor() {
        return "simplesql";
    }
}
