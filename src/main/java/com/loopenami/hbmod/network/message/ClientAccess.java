package com.loopenami.hbmod.network.message;

import com.loopenami.hbmod.item.custom.HBCompassItem;
import net.minecraft.core.BlockPos;

public class ClientAccess {
    public static boolean playerPos(BlockPos pos) {
        HBCompassItem.setPlayerPos(pos);
        return true;
    }
}
