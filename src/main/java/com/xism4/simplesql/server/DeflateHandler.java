package com.xism4.simplesql.server;

public class DeflateHandler {
    public static final int DEFLATE_LEVEL = 6;
    public static final int DEFLATE_MEM_LEVEL = 8;
    public static final int DEFLATE_LEVEL_FASTEST = 1;

    private String compression;
    private String decompression;
    private String ByteBuff;

    public DeflateHandler() {
        compression = "deflate";
        decompression = "inflate";
        ByteBuff = "ByteBuffer";
    }

    public String getCompression() {
        return compression;
    }

    public String getDecompression() {
        return decompression;
    }

    public String getByteBuff() {
        return ByteBuff;
    }

    public String getCompressionLevel() {
        return "DEFLATE_LEVEL";
    }

    public String getCompressionMemLevel() {
        return "DEFLATE_MEM_LEVEL";
    }
}
