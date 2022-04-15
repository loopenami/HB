package com.loopenami.hbmod.item.custom;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class KnockbackSwordItem extends SwordItem {

    public KnockbackSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if(pAttacker instanceof Player pPlayer) {
            pPlayer.getCooldowns().addCooldown(this, 200);
        }
        pTarget.knockback(100,(double) Mth.sin(pTarget.getYRot() * ((float)Math.PI / 180F)), (double)(-Mth.cos(pTarget.getYRot() * ((float)Math.PI / 180F))));
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
