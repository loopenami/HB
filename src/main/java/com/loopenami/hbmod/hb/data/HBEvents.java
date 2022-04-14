package com.loopenami.hbmod.hb.data;

import com.loopenami.hbmod.HBM;
import com.loopenami.hbmod.hb.client.ClientTraverseData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;


public class HBEvents {
    public static final String HBT = "hbsgaloltd";
    private static int count = 0;

    public static void onAttachCapabilitiesToPlayer(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(HBIsTraversingProvider.HB_IS_TRAVERSING).isPresent()) {
                // The player does not already have this capability so we need to add the capability provider here
                event.addCapability(new ResourceLocation(HBM.MOD_ID, "transverse"), new HBIsTraversingProvider());
            }
        }
    }

    public static void onRegisterAbilities(RegisterCapabilitiesEvent event) {
        event.register(HBIsTraversing.class);
    }

    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        event.world.players().forEach(player -> {
            if ((player instanceof ServerPlayer serverPlayer) && serverPlayer.getCapability(HBIsTraversingProvider.HB_IS_TRAVERSING).map(HBIsTraversing::isTraversing).orElse(false)) {
                HBAbilityManager.tick(event.world);
            }
        });
    }

    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        int counter = 0;
        if(event.player.getCapability(HBIsTraversingProvider.HB_IS_TRAVERSING).isPresent()){
        if(ClientTraverseData.isTraversing()) {
            event.player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY,20));
        } else{
            event.player.removeEffect(MobEffects.INVISIBILITY);
        }
        }
    }
}
