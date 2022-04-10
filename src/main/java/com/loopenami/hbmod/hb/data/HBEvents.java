package com.loopenami.hbmod.hb.data;

import com.loopenami.hbmod.HBM;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;


public class HBEvents {
    public static final String HBT = "hbsgaloltd";
    private static int count = 0;
    public static void onAttachCapabilitiesToPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
                event.addCapability(new ResourceLocation(HBM.MOD_ID, "istraversing"), new HBTraversingProvider());
        }
    }

    public static void onRegisterAbilities(RegisterCapabilitiesEvent event) {
        event.register(HBTraversing.class);
    }

    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.world.isClientSide) {
            return;
        }
        if (event.phase == TickEvent.Phase.START) {
            return;
        }

//        event.world.players().forEach(player -> {
//            if(player instanceof ServerPlayer serverPlayer) {
//                count--;
//                if (count == 0 && player.getCapability(HBTraversingProvider.HB_TRAVERSING)
//                        .map(HBTraversing::isTraversing)
//                        .orElse(false)) {
//                    count = 1200;
//                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 1200));
//                }
//            }
//        });

    }
}
