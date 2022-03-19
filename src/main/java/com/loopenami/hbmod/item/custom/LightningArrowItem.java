package com.loopenami.hbmod.item.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;

public class LightningArrowItem extends Arrow {
    public LightningArrowItem(EntityType<? extends Arrow> p_36858_, Level p_36859_) {
        super(p_36858_, p_36859_);
    }

    public LightningArrowItem(Level p_36861_, double p_36862_, double p_36863_, double p_36864_) {
        super(p_36861_, p_36862_, p_36863_, p_36864_);
    }

    public LightningArrowItem(Level p_36866_, LivingEntity p_36867_) {
        super(p_36866_, p_36867_);
    }
}
