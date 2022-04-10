package com.loopenami.hbmod.network.message;

import com.loopenami.hbmod.HBM;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    public static final String PROTOCOL_VERSION = "0.1.0";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(HBM.MOD_ID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    private PacketHandler() {

    }

    public static void init() {
        int index = 0;
        INSTANCE.messageBuilder(HBCMessageSC.class, index++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(HBCMessageSC::encode).decoder(HBCMessageSC::new)
                .consumer(HBCMessageSC::handle).add();
    }
}
