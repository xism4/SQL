package com.azoraqua.simplesql;

import com.azoraqua.simplesql.server.Server;

public final class QSimpleSQL {

    // Suppresses default constructor, ensuring non-instantiability.
    private QSimpleSQL() {
        throw new AssertionError();
    }

    public static Server create(Server.DSN dsn) {
        return Server.factory().create(dsn);
    }

    public static Server create(String driver, String host, String username, String password, String schema) {
        return Server.factory().create(driver, host, username, password, schema);
    }
}
