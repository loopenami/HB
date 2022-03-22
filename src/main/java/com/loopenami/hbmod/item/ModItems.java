package com.loopenami.hbmod.item;

import com.loopenami.hbmod.HBM;
import com.loopenami.hbmod.item.custom.BlindnessArrowItem;
import com.loopenami.hbmod.item.custom.LightningArrowItem;
import com.loopenami.hbmod.item.custom.PoisonSwordItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HBM.MOD_ID);

    public static final RegistryObject<Item> CHA0S = ITEMS.register("chaos",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> CHA0S_SWORD = ITEMS.register("chaos_sword",
            () -> new PoisonSwordItem(ModTiers.CHA0S, 1, 4f,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> LIGHTNING_ARROW = ITEMS.register("lightning_arrow", () -> new LightningArrowItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC), 0.5F));
    public static final RegistryObject<Item> BLINDING_ARROW = ITEMS.register("blinding_arrow", () -> new BlindnessArrowItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC), 0.5F));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
