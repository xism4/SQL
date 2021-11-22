package com.xism4.simplesql.server;

public abstract class Server {

    private static final ServerFactory SERVER_FACTORY = new ServerFactory();
    private static final ServerBuilder DSN_BUILDER = new ServerBuilder();

    private final String host;
    private final String username;
    private final String password;
    private final String schema;

    // Implementations should override it, implementations should override it.
    public Server(String host, String username, String password, String schema) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.schema = schema;
    }

    // Only for internal purposes, implementations should override it.
    protected Server() {
        this(null, null, null, null);
    }

    public abstract String getName();

    public abstract String getVendor();

    public static ServerFactory factory() {
        return SERVER_FACTORY;
    }

    public static final class DSN {

        protected final String driver;
        protected final String host;
        protected final String username;
        protected final String password;
        protected final String schema;

        public DSN(String driver, String host, String username, String password, String schema) {
            this.driver = driver;
            this.host = host;
            this.username = username;
            this.password = password;
            this.schema = schema;
        }

        // <driver>://<username>:<password>@<host>:<port>/<schema>
        public static DSN from(String dsn) {
            throw new UnsupportedOperationException("Not implemented yet.");
        }

        public static ServerBuilder builder() {
            return DSN_BUILDER;
        }
    }
}
