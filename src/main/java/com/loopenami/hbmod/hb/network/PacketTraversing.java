package com.loopenami.hbmod.hb.network;

import com.loopenami.hbmod.hb.data.HBTraversing;
import com.loopenami.hbmod.hb.data.HBTraversingProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketTraversing {
    public PacketTraversing() {
    }

    public PacketTraversing(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf){
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            assert player != null;
                boolean hbTraversing = player.getCapability(HBTraversingProvider.HB_TRAVERSING)
                        .map(HBTraversing::isTraversing)
                        .orElse(false);
                EntityType.LIGHTNING_BOLT.spawn(player.getLevel(), null, null, player.blockPosition(), MobSpawnType.TRIGGERED, true, true);
//                if (hbTraversing) {
//                    player.getCapability(HBTraversingProvider.HB_TRAVERSING).ifPresent(HBTraversing -> HBTraversing.setTraversing(false));
//                }
//                if (!hbTraversing) {
//                    player.getCapability(HBTraversingProvider.HB_TRAVERSING).ifPresent(HBTraversing -> HBTraversing.setTraversing(true));
//                }
        });
        return true;
    }

}
