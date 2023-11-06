package com.xism4.simplesql;


import com.xism4.simplesql.server.Server;

public final class DatabaseTest extends Server {

    public DatabaseTest(String host, String username, String password, String schema) {
        super(host, username, password, schema);
    }

    public DatabaseTest() {
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
