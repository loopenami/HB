package com.loopenami.hbmod.world.entity.projectile;

import com.loopenami.hbmod.world.entity.ModEntityType;
import com.loopenami.hbmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class LightningArrow extends AbstractArrow {
    private final Item referenceItem;

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);


    }

    public LightningArrow(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
        this.referenceItem = ModItems.LIGHTNING_ARROW.get();
    }

    public LightningArrow(LivingEntity shooter, Level level, Item referenceItem) {
        super(ModEntityType.LIGHTNING_ARROW.get(), shooter, level);
        this.referenceItem = referenceItem;
    }

    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }


}
