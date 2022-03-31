package com.loopenami.hbmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SuperBlindEffect extends MobEffect {
    public SuperBlindEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

    }

    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
       return true;
    }
}
