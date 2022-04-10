package com.loopenami.hbmod.hb.data;

import net.minecraft.nbt.CompoundTag;

public class HBTraversing {

    private boolean isTraversing;

    public boolean isTraversing() {
        return isTraversing;
    }

    public void setTraversing(boolean traversing) {
        isTraversing = traversing;
    }

    public boolean getTraversing() {
        return isTraversing;
    }

    public void saveNBTData(CompoundTag compound) {
        compound.putBoolean("istraversing", this.isTraversing);
    }
    public void loadNBTData(CompoundTag compound) {
        this.isTraversing = compound.getBoolean("istraversing");
    }
}
