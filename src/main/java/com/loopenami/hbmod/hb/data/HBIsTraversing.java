package com.loopenami.hbmod.hb.data;

import net.minecraft.nbt.CompoundTag;

public class HBIsTraversing {
    //stores boolean from keybind
        private boolean isTraversing;


        public void setTraversing(boolean isTraversing) {
            this.isTraversing = isTraversing;
        }

        public void changeTraverseStatus() {
            this.isTraversing = !this.isTraversing;
        }

        public void copyFrom(HBIsTraversing source) {
            isTraversing = source.isTraversing;
        }


        public void saveNBTData(CompoundTag compound) {
            compound.putBoolean("istraversing", isTraversing);
        }

        public void loadNBTData(CompoundTag compound) {
            isTraversing = compound.getBoolean("istraversing");
        }

    public boolean isTraversing() {
        return isTraversing;
    }
}
