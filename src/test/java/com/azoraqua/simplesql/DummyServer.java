package com.azoraqua.simplesql;

import com.azoraqua.simplesql.server.Server;

public final class DummyServer extends Server {

    public DummyServer(String host, String username, String password, String schema) {
        super(host, username, password, schema);
    }

    protected DummyServer() {
        super();
    }

    @Override
    public String getName() {
        return "dummy";
    }

    @Override
    public String getVendor() {
        return "qsimplesql";
    }
}
