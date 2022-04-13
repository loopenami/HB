package com.loopenami.hbmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class TriggerChestBlock extends ChestBlock {
    public TriggerChestBlock(BlockBehaviour.Properties p_57573_) {
        super(p_57573_, () -> BlockEntityType.TRAPPED_CHEST);
    }

    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TrappedChestBlockEntity(pPos, pState);
    }

    protected Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
    }
}
