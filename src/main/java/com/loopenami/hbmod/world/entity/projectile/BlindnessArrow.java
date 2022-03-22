package com.loopenami.hbmod.world.entity.projectile;

import com.loopenami.hbmod.item.ModItems;
import com.loopenami.hbmod.world.entity.ModEntityTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class BlindnessArrow extends AbstractArrow {
    private final Item referenceItem;

    public BlindnessArrow(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
        this.referenceItem = ModItems.BLINDING_ARROW.get();
    }

    public BlindnessArrow(LivingEntity shooter, Level level, Item referenceItem) {
        super(ModEntityTypes.BLINDING_ARROW.get(), shooter, level);
        this.referenceItem = referenceItem;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        if (entity instanceof LivingEntity livingentity) {
            livingentity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,500));
        }
    }

    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }
}
