package com.loopenami.hbmod.block.entity.server;

import com.loopenami.hbmod.HBM;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(HBM.MOD_ID,"main"),() -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    private PacketHandler(){
    }

    public static void init() {
        int index = 0;
        INSTANCE.messageBuilder(null, index, null);
    }
}
