package com.loopenami.hbmod.network.message;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class HBCMessageSC {
    private int index;
    private BlockPos playerPos;

    public HBCMessageSC(int ticker){
        this.index += ticker;
    }

    public HBCMessageSC (FriendlyByteBuf buffer) {
        this(buffer.readInt());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.index);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        if (this.index > ctx.get().getSender().level.players().size()) {
            this.index = 0;
        }
         this.playerPos = ctx.get().getSender().level.players().get(index).blockPosition();
        ctx.get().enqueueWork(()-> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> success.set(ClientAccess.playerPos(this.playerPos)));
        });
        return success.get();
    }
}
