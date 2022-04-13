package com.loopenami.hbmod.hb.network;

import com.loopenami.hbmod.hb.data.HBAbilityProvider;
import com.loopenami.hbmod.hb.data.HBIsTraversing;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
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
            player.getCapability(HBAbilityProvider.HB_IS_TRAVERSING).ifPresent(HBIsTraversing::changeTraverseStatus);
        });
        return true;
    }

}
