package com.loopenami.hbmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ExplosionPickaxeItem extends PickaxeItem {
    public ExplosionPickaxeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (!(pState.getBlock() instanceof FireBlock)) {
            pLevel.explode(null, pPos.getX(), pPos.getY(), pPos.getZ(), 5.0f, false, Explosion.BlockInteraction.DESTROY);
        }
        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }
}