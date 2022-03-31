package com.loopenami.hbmod.world.entity;

import com.loopenami.hbmod.HBM;
import com.loopenami.hbmod.world.entity.projectile.LightningArrow;
import com.loopenami.hbmod.world.entity.projectile.BlindingArrow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, HBM.MOD_ID);

    public static final RegistryObject<EntityType<LightningArrow>> LIGHTNING_ARROW = ENTITIES.register("lightning_arrow", () -> EntityType.Builder.<LightningArrow>of(LightningArrow::new, MobCategory.MISC).sized(0.5F,0.5F).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(HBM.MOD_ID, "lightning_arrow").toString()));
    public static final RegistryObject<EntityType<BlindingArrow>> BLINDING_ARROW = ENTITIES.register("blinding_arrow", () -> EntityType.Builder.<BlindingArrow>of(BlindingArrow::new, MobCategory.MISC).sized(0.5F,0.5F).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(HBM.MOD_ID, "blinding_arrow").toString()));


    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
