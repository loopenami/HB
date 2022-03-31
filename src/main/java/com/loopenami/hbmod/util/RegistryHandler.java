package com.loopenami.hbmod.util;

import com.loopenami.hbmod.world.entity.ModEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class RegistryHandler {
    public static void init(){
        ModEntityType.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
