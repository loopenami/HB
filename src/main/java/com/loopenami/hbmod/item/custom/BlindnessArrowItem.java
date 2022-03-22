package com.loopenami.hbmod.item.custom;

import com.loopenami.hbmod.world.entity.projectile.BlindnessArrow;
import com.loopenami.hbmod.item.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlindnessArrowItem extends ArrowItem {
    private final float damage;
    public BlindnessArrowItem(Item.Properties properties, float damage) {
        super(properties);
        this.damage = damage;
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        BlindnessArrow arrow = new BlindnessArrow(pShooter, pLevel, ModItems.LIGHTNING_ARROW.get());
        return arrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        return true;
    }
}
