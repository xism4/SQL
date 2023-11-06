package com.xism4.simplesql;

import com.xism4.simplesql.server.ServerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public final class DatabaseTestImpl {

    @BeforeAll
    public static void setup() {
        ServerFactory.register(new DatabaseTest());
    }

    @Test
    public void test() {
        Assertions.assertEquals("test", SimpleSQL.create("database").getName());
    }

    @AfterAll
    public static void tearDown() {
        ServerFactory.unregister(DatabaseTest.class);
    }
}
