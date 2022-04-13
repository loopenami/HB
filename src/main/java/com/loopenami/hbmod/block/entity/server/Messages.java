package com.loopenami.hbmod.block.entity.server;

import com.loopenami.hbmod.HBM;
import com.loopenami.hbmod.hb.network.PacketTraversing;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Messages {
    private static SimpleChannel INSTANCE;
    private static int pocketId = 1;
    private static int id() {return pocketId++;}

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(HBM.MOD_ID,"messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(PacketTraversing.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketTraversing::new)
                .encoder(PacketTraversing::toBytes)
                .consumer(PacketTraversing::handle)
                .add();
//        net.messageBuilder(PacketSyncTraversingToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
//                .decoder(PacketSyncTraversingToClient::new)
//                .encoder(PacketSyncTraversingToClient::toBytes)
//                .consumer(PacketSyncTraversingToClient::handle)
//                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
