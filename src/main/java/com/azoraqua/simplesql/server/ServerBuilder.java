package com.azoraqua.simplesql.server;

public final class ServerBuilder {

    private String driver;
    private String host;
    private String username;
    private String password;
    private String schema;

    // Only for internal purposes.
    protected ServerBuilder() {
    }

    public ServerBuilder withDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public ServerBuilder withHost(String host) {
        this.host = host;
        return this;
    }

    public ServerBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public ServerBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public ServerBuilder withSchema(String schema) {
        this.schema = schema;
        return this;
    }

    public Server.DSN build() {
        return new Server.DSN(driver, host, username, password, schema);
    }
}
