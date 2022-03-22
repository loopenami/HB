package com.loopenami.hbmod.client.renderer.entity;

import com.loopenami.hbmod.HBM;
import com.loopenami.hbmod.world.entity.projectile.BlindnessArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlindingArrowRenderer extends ArrowRenderer<BlindnessArrow> {
    @OnlyIn(Dist.CLIENT)
    public BlindingArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(BlindnessArrow pEntity) {
        Item referenceItem = pEntity.getPickupItem().getItem();
        return new ResourceLocation(HBM.MOD_ID,"textures/entity/projectiles/" + referenceItem.getRegistryName().getPath() + ".png");
    }
}
