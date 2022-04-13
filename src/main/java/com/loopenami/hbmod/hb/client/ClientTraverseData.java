package com.loopenami.hbmod.hb.client;

public class ClientTraverseData {

    private static boolean isTraversing;

    public static void set(boolean isTraversing) {
        ClientTraverseData.isTraversing = isTraversing;
    }

    public static boolean isTraversing() {
        return isTraversing;
    }
}
