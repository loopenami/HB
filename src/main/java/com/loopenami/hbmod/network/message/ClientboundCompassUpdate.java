package com.loopenami.hbmod.network.message;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ClientboundCompassUpdate {
    private static int playerNumber = 0;
    private BlockPos playerPos;

    public ClientboundCompassUpdate(int cycle) {
        playerNumber += cycle;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(playerNumber);
    }

    public ClientboundCompassUpdate(FriendlyByteBuf buffer) {
        this(buffer.readInt());
    }


    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            final List<ServerPlayer> playerList = Objects.requireNonNull(Objects.requireNonNull(ctx.get().getSender()).level.getServer()).getPlayerList().getPlayers();
            if (playerNumber >= playerList.size()) {
                playerNumber = 0;
            }
            this.playerPos = playerList.get(playerNumber).blockPosition();

            success.set(ClientAccess.playerPos(this.playerPos));
        });
        ctx.get().setPacketHandled(true);
        return success.get();
    }
}

