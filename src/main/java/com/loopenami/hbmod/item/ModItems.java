package com.loopenami.hbmod.item;

import com.loopenami.hbmod.HBM;
import com.loopenami.hbmod.item.custom.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HBM.MOD_ID);

    public static final RegistryObject<Item> CHA0S = ITEMS.register("chaos",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> POISON_SWORD = ITEMS.register("poison_sword",
            () -> new PoisonSwordItem(ModTiers.CHA0S, 1, 4f,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> KNOCKBACK_SWORD = ITEMS.register("knockback_sword",
            () -> new KnockbackSwordItem(ModTiers.CHA0S, 1, 4f,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> EXPLOSION_PICKAXE = ITEMS.register("explosion_pickaxe", () -> new ExplosionPickaxeItem(Tiers.NETHERITE, 1, 4f,
            new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> CRAWLIES_ARROW = ITEMS.register("crawlies_arrow", () -> new CrawliesArrowItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC), 0.5F));
    public static final RegistryObject<Item> BLINDING_ARROW = ITEMS.register("blinding_arrow", () -> new BlindingArrowItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC), 0.5F));

    public static final RegistryObject<Item> PLAYER_COMPASS = ITEMS.register("player_compass", () -> new PlayerCompassItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).fireResistant()));
    public static final RegistryObject<Item> HB_COMPASS = ITEMS.register("hb_compass", () -> new HBCompassItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).fireResistant()));

    public static final RegistryObject<Item> LILY_KEY = ITEMS.register("lily_key", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).fireResistant()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
