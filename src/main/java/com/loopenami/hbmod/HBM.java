package com.loopenami.hbmod;

import com.loopenami.hbmod.block.ModBlocks;
import com.loopenami.hbmod.block.entity.ModBlockEntities;
import com.loopenami.hbmod.block.entity.server.Messages;
import com.loopenami.hbmod.hb.client.KeyBindings;
import com.loopenami.hbmod.hb.client.KeyInputHandler;
import com.loopenami.hbmod.hb.data.HBEvents;
import com.loopenami.hbmod.item.ModItems;
import com.loopenami.hbmod.network.message.PacketHandler;
import com.loopenami.hbmod.util.ModItemProperties;
import com.loopenami.hbmod.world.entity.ModEntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HBM.MOD_ID)
public class HBM {
    public static final String MOD_ID = "hbmod";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    // Very important Comment
    public HBM() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);

        ModBlocks.register(eventBus);

        ModEntityType.register(FMLJavaModLoadingContext.get().getModEventBus());

        ModBlockEntities.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);


        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, HBEvents::onAttachCapabilitiesToPlayer);
        MinecraftForge.EVENT_BUS.addListener(HBEvents::onWorldTick);


        //HB
        eventBus.addListener(HBEvents::onRegisterAbilities);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ModItemProperties.addCustomItemProperties();
        MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onKeyInput);

        KeyBindings.init();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        Messages.register();
        PacketHandler.init();

    }
}
