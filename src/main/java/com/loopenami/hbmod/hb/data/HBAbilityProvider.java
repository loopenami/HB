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
    public static Capability<HBAbilities> HB_TRAVERSING = CapabilityManager.get(new CapabilityToken<>(){});

    private HBAbilities HBAbilities = null;
    private final LazyOptional<HBAbilities> opt = LazyOptional.of(this::createHBAbilities);

    private HBAbilities createHBAbilities() {
        if (HBAbilities == null) {
            HBAbilities = new HBAbilities();
        }
        return HBAbilities;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createHBAbilities().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createHBAbilities().loadNBTData(nbt);
    }

}
