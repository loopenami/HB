package com.loopenami.hbmod.hb.data;

import net.minecraft.nbt.CompoundTag;

public class HBAbilities {

    private boolean isAble;

    public boolean isAble() {
        return isAble;
    }

    public void setAbilities(boolean traversing) {
        isAble = traversing;
    }


    public void saveNBTData(CompoundTag compound) {
        compound.putBoolean("isable", this.isAble);
    }
    public void loadNBTData(CompoundTag compound) {
        this.isAble = compound.getBoolean("isable");
    }
}
