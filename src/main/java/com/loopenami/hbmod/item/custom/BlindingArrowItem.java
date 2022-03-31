package com.loopenami.hbmod.item.custom;

import com.loopenami.hbmod.world.entity.projectile.BlindingArrow;
import com.loopenami.hbmod.item.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlindingArrowItem extends ArrowItem {
    private final float damage;
    public BlindingArrowItem(Item.Properties properties, float damage) {
        super(properties);
        this.damage = damage;
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        BlindingArrow arrow = new BlindingArrow(pShooter, pLevel, ModItems.BLINDING_ARROW.get());
        arrow.setBaseDamage(this.damage);
        return arrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        return true;
    }
}
