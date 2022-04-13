package com.loopenami.hbmod.hb.data;

import com.loopenami.hbmod.block.entity.server.Messages;
import com.loopenami.hbmod.hb.network.PacketSyncTraversingToClient;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class HBAbilityManager {
    private static int counter = 0;

    public static void tick(Level level) {
        counter--;
        // Every 10 ticks this code will synchronize the mana of each player and the mana of the current
        // chunk of that player to the client so that it can be displayed on screen
        if (counter <= 0) {
            counter = 10;
            // Synchronize the mana to the players in this world
            // todo expansion: keep the previous data that was sent to the player and only send if changed
            level.players().forEach(player -> {
                if (player instanceof ServerPlayer serverPlayer) {
                    boolean isTraversing = serverPlayer.getCapability(HBAbilityProvider.HB_IS_TRAVERSING)
                            .map(HBIsTraversing::isTraversing)
                            .orElse(false);
                    Messages.sendToPlayer(new PacketSyncTraversingToClient(isTraversing), serverPlayer);
                }
            });

            // todo expansion: here it would be possible to slowly regenerate mana in chunks
        }
    }
}
