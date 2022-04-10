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

public class HBTraversingProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<HBTraversing> HB_TRAVERSING = CapabilityManager.get(new CapabilityToken<>(){});

    private HBTraversing HBTraversing = null;
    private final LazyOptional<HBTraversing> opt = LazyOptional.of(this::createHBTraversing);

    private HBTraversing createHBTraversing() {
        if (HBTraversing == null) {
            HBTraversing = new HBTraversing();
        }
        return HBTraversing;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createHBTraversing().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createHBTraversing().loadNBTData(nbt);
    }

}
