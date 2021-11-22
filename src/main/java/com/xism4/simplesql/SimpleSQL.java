package com.xism4.simplesql;

import com.xism4.simplesql.server.Server;

public final class SimpleSQL {

    // Suppresses default constructor, ensuring non-instantiability.
    private SimpleSQL() {
        throw new AssertionError();
    }

    public static Server create(Server.DSN dsn) {
        return Server.factory().create(dsn);
    }

    public static Server create(String driver, String host, String username, String password, String schema) {
        return Server.factory().create(driver, host, username, password, schema);
    }

    public static Server create(String driver) {
        return Server.factory().create(driver);
    }
}
