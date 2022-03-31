package com.loopenami.hbmod.world.entity.projectile;

import com.loopenami.hbmod.item.ModItems;
import com.loopenami.hbmod.world.entity.ModEntityType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class BlindingArrow extends AbstractArrow {
    private final Item referenceItem;


    public BlindingArrow(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
        this.referenceItem = ModItems.BLINDING_ARROW.get();
    }

    public BlindingArrow(LivingEntity shooter, Level level, Item referenceItem) {
        super(ModEntityType.BLINDING_ARROW.get(), shooter, level);
        this.referenceItem = referenceItem;
    }



    @Override
    protected void onHitEntity(EntityHitResult pLiving) {
        if(pLiving.getEntity() instanceof LivingEntity pTarget) {
            pTarget.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200));
        }
    }

    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }
}
