package com.loopenami.hbmod.client.renderer.entity;

import com.loopenami.hbmod.HBM;
import com.loopenami.hbmod.world.entity.projectile.CrawliesArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LightningArrowRenderer extends ArrowRenderer<CrawliesArrow> {
    @OnlyIn(Dist.CLIENT)
    public LightningArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(CrawliesArrow pEntity) {
        Item referenceItem = pEntity.getPickupItem().getItem();
        return new ResourceLocation(HBM.MOD_ID,"textures/entity/projectiles/" + referenceItem.getRegistryName().getPath() + ".png");
    }
}
