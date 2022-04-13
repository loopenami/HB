package com.loopenami.hbmod.hb.data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HBAbilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<HBIsTraversing> HB_IS_TRAVERSING = CapabilityManager.get(new CapabilityToken<>(){});

    private HBIsTraversing hbIsTraversing = null;
    private final LazyOptional<HBIsTraversing> opt = LazyOptional.of(this::createHBIsTraversing);

    @Nonnull
    private HBIsTraversing createHBIsTraversing() {
        if (hbIsTraversing == null) {
            hbIsTraversing = new HBIsTraversing();
        }
        return hbIsTraversing;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == HB_IS_TRAVERSING) {
            return opt.cast();
        }
        return LazyOptional.empty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createHBIsTraversing().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createHBIsTraversing().loadNBTData(nbt);
    }
}