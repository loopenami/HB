package com.loopenami.hbmod.hb.network;

import com.loopenami.hbmod.hb.client.ClientTraverseData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSyncTraversingToClient {

    private final boolean isTraversing;

    public PacketSyncTraversingToClient(boolean isTraversing) {
        this.isTraversing = isTraversing;
    }

    public PacketSyncTraversingToClient(FriendlyByteBuf buf) {
        isTraversing = buf.readBoolean();
     }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(isTraversing);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // Here we are client side.
            // Be very careful not to access client-only classes here! (like Minecraft) because
            // this packet needs to be available server-side too
            ClientTraverseData.set(isTraversing);
        });

        return true;
    }
}
