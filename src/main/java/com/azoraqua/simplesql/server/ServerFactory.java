package com.azoraqua.simplesql.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ServerFactory {

    private static final Map<String, Class<? extends Server>> map = new ConcurrentHashMap<>();

    // Only for internal purposes.
    protected ServerFactory() {
    }

    /**
     * @param dsn The DSN.
     * @return The constructed server.
     * @throws IllegalArgumentException When the driver parameter is null.
     * @throws IllegalStateException    When the driver does not have an accessible constructor.
     * @since 1.0.0
     */
    public static <T extends Server> T create(Server.DSN dsn) {
        if (dsn == null) {
            throw new IllegalArgumentException("DSN cannot be null.");
        }

        return create(dsn.driver, dsn.host, dsn.username, dsn.password, dsn.schema);
    }

    /**
     * @param driver   The driver to create a server with.
     * @param host     The host.
     * @param username The username.
     * @param password The password.
     * @param schema   The schema.
     * @return The constructed server.
     * @throws IllegalArgumentException When the driver parameter is null.
     * @throws IllegalStateException    When the driver is not registered.
     * @throws IllegalStateException    When the driver does not have an accessible constructor.
     * @since 1.0.0
     */
    @SuppressWarnings({"unchecked", "deprecation"})
    // AccessibleObject.isAccessible is deprecated but should be fine for some time.
    public static <T extends Server> T create(String driver, String host, String username, String password, String schema) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null.");
        }

        try {
            final Optional<? extends Class<? extends Server>> opt = ServerFactory.find(driver);

            if (opt.isPresent()) {
                final Constructor<? extends Server> constructor = opt.get().getDeclaredConstructor(String.class, String.class, String.class, String.class);
                final boolean accessible = constructor.isAccessible();

                constructor.setAccessible(true);
                final Server server = constructor.newInstance(host, username, password, schema);
                constructor.setAccessible(accessible);

                return (T) server;
            } else {
                throw new IllegalStateException("No driver registered with that name.");
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalStateException("Driver does not have an accessible constructor.");
        }
    }

    /**
     * @param driver The driver to create a server with.
     * @return The constructed server.
     * @throws IllegalArgumentException When the driver parameter is null.
     * @throws IllegalStateException    When the driver does not have an accessible constructor.
     * @since 1.0.0
     */
    @SuppressWarnings({"unchecked", "deprecation"})
    // AccessibleObject.isAccessible is deprecated but should be fine for some time.
    public static <T extends Server> T create(String driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null.");
        }

        try {
            final Optional<? extends Class<? extends Server>> opt = ServerFactory.find(driver);

            if (opt.isPresent()) {
                final Constructor<? extends Server> constructor = opt.get().getDeclaredConstructor();
                final boolean accessible = constructor.isAccessible();

                constructor.setAccessible(true);
                final Server server = constructor.newInstance();
                constructor.setAccessible(accessible);

                return (T) server;
            } else {
                throw new IllegalStateException("No driver registered with that name.");
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalStateException("Driver does not have an accessible constructor.");
        }
    }

    /**
     * Registers a server implementation in the factory.
     *
     * @param server The server to be registered.
     * @throws IllegalArgumentException When the server parameter is null.
     * @throws IllegalStateException    When the name of the server is not provided.
     * @throws IllegalStateException    when the vendor of the server is not provided.
     * @since 1.0.0
     */
    public static void register(Server server) {
        if (server == null) {
            throw new IllegalArgumentException("Server cannot be null.");
        }

        final String implName = server.getName();
        final String implVendor = server.getVendor();

        if (implName.isBlank()) {
            throw new IllegalStateException("Implementation name may not be empty.");
        }

        if (implVendor.isBlank()) {
            throw new IllegalStateException("Implementation vendor may not be empty.");
        }

        map.put((implVendor + ":" + implName).toLowerCase(), server.getClass());
    }

    /**
     * @param driverClass The class of the driver to be unregistered.
     * @throws IllegalArgumentException When the driver-class parameter is null.
     * @since 1.0.0
     */
    public static void unregister(Class<? extends Server> driverClass) {
        if (driverClass == null) {
            throw new IllegalArgumentException("Driver-class cannot be null.");
        }

        map.values().remove(driverClass);
    }

    private static Optional<? extends Class<? extends Server>> find(String driver) {
        return map.entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase(driver) || e.getKey().endsWith(driver)).map(Map.Entry::getValue).findAny();
    }
}
