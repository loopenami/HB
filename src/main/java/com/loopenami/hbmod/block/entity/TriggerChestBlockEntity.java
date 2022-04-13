package com.loopenami.hbmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

public class TriggerChestBlockEntity extends ChestBlockEntity{

    public TriggerChestBlockEntity(BlockPos p_155862_, BlockState p_155863_) {
        super(BlockEntityType.TRAPPED_CHEST, p_155862_, p_155863_);
    }

    protected void signalOpenCount(Level chestLevel, BlockPos chestPos, BlockState p_155867_, int p_155868_, int p_155869_) {
        super.signalOpenCount(chestLevel, chestPos, p_155867_, p_155868_, p_155869_);
        if (p_155868_ != p_155869_) {
            Block block = p_155867_.getBlock();
            chestLevel.updateNeighborsAt(chestPos, block);
            chestLevel.updateNeighborsAt(chestPos.below(), block);
            int chestX = chestPos.getX();
            int chestY = chestPos.getY();
            int chestZ = chestPos.getZ();
            int behindChestX = chestX - 1;
            int inFrontOfChestX = chestX +1;
            int behindChestZ = chestZ - 1;
            int inFrontOfChestZ = chestZ +1;
            BlockPos adjacent0 = new BlockPos(behindChestX, chestY, behindChestZ);
            BlockPos adjacent1 = new BlockPos(behindChestX, chestY, inFrontOfChestZ);
            BlockPos adjacent2 = new BlockPos(inFrontOfChestX, chestY, behindChestZ);
            BlockPos adjacent3 = new BlockPos(inFrontOfChestX, chestY, inFrontOfChestZ);
            BlockPos adjacent4 = new BlockPos(chestX, chestY, inFrontOfChestZ);
            BlockPos adjacent5 = new BlockPos(chestX, chestY, behindChestZ);
            BlockPos adjacent6 = new BlockPos(behindChestX, chestY, chestZ);
            BlockPos adjacent7 = new BlockPos(inFrontOfChestX, chestY, chestZ);
            BlockPos[] values = new BlockPos[8];

            values[0] = adjacent0;
            values[1] = adjacent1;
            values[2] =adjacent2;
            values[3] = adjacent3;
            values[4] =adjacent4;
            values[5] = adjacent5;
            values[6] = adjacent6;
            values[7] =adjacent7;
            for (BlockPos value : values) {
                Objects.requireNonNull(getLevel()).setBlock(value.below(), Blocks.MAGMA_BLOCK.defaultBlockState(), 2);
                BlockPos waterFill = value;
                while (chestLevel.getBlockState(waterFill.above()).is(Blocks.WATER)) {
                    int waterY = waterFill.getY() + 1;
                    getLevel().setBlock(waterFill, Blocks.WATER.defaultBlockState(), 2);
                    waterFill = new BlockPos(value.getX(), waterY, value.getZ());
                }
            }
        }

    }
}
