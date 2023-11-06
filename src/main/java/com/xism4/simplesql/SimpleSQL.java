package com.xism4.simplesql;

import com.xism4.simplesql.server.DeflateHandler;
import com.xism4.simplesql.server.Server;
import com.xism4.simplesql.server.ServerFactory;

public final class SimpleSQL {

    // Suppresses default constructor, ensuring non-instability.
    private SimpleSQL() {
        throw new AssertionError();
    }

    public static Server create(Server.DSN dsn) {
        return ServerFactory.create(dsn);
    }

    public static Server create(String driver, String host, String username, String password, String schema) {
        return ServerFactory.create(driver, host, username, password, schema);
    }
    public static Server create(String driver) {
        return ServerFactory.create(driver);
    }
}
