package com.loopenami.hbmod.util;

import com.loopenami.hbmod.world.entity.ModEntityTypes;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class RegistryHandler {
    public static void init(){
        ModEntityTypes.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
