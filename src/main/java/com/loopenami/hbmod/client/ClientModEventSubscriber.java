package com.loopenami.hbmod.client;

import com.loopenami.hbmod.HBM;
import com.loopenami.hbmod.client.renderer.entity.LightningArrowRenderer;
import com.loopenami.hbmod.world.entity.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HBM.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.LIGHTNING_ARROW.get(), LightningArrowRenderer::new);
    }
}
