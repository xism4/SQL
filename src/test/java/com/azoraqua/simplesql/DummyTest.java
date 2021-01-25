package com.azoraqua.simplesql;

import com.azoraqua.simplesql.server.ServerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public final class DummyTest {

    @BeforeAll
    public static void setup() {
        ServerFactory.register(new DummyServer());
    }

    @Test
    public void test() {
        Assertions.assertEquals("dummy", QSimpleSQL.create("dummy").getName());
    }

    @AfterAll
    public static void tearDown() {
        ServerFactory.unregister(DummyServer.class);
    }
}
