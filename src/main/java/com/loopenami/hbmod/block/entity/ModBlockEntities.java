package com.loopenami.hbmod.block.entity;

import com.loopenami.hbmod.HBM;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, HBM.MOD_ID);

//    public static final RegistryObject<BlockEntityType<TriggerChestBlockEntity>> TRIGGER_CHEST = BLOCK_ENTITIES.register("trigger_chest",() ->
//            BlockEntityType.Builder.of(TriggerChestBlockEntity::new, ModBlocks.));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
