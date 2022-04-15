package com.loopenami.hbmod.util;

import com.loopenami.hbmod.item.ModItems;
import com.loopenami.hbmod.item.custom.PlayerCompassItem;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Optional;

public class ModItemProperties {
public static void addCustomItemProperties() {
makeCompass(ModItems.PLAYER_COMPASS.get());
//makeCompass(ModItems.HB_COMPASS.get());
}

private static void makeCompass(Item item) {
    ItemProperties.register(item, new ResourceLocation("angles"), new ClampedItemPropertyFunction() {
        private final ModItemProperties.CompassWobble wobble = new ModItemProperties.CompassWobble();
        private final ModItemProperties.CompassWobble wobbleRandom = new ModItemProperties.CompassWobble();

        public float unclampedCall(ItemStack compassInHand, @Nullable ClientLevel p_174673_, @Nullable LivingEntity p_174674_, int p_174675_) {
            Entity entity = (Entity)(p_174674_ != null ? p_174674_ : compassInHand.getEntityRepresentation());
            if (entity == null) {
                return 0.0F;
            } else {
                if (p_174673_ == null && entity.level instanceof ClientLevel) {
                    p_174673_ = (ClientLevel)entity.level;
                }

                BlockPos blockpos = CompassItem.isLodestoneCompass(compassInHand) ? this.getLodestonePosition(p_174673_, compassInHand.getOrCreateTag()) : null;
                long i = p_174673_.getGameTime();
                if (blockpos != null && !(entity.position().distanceToSqr((double)blockpos.getX() + 0.5D, entity.position().y(), (double)blockpos.getZ() + 0.5D) < (double)1.0E-5F)) {
                    boolean flag = p_174674_ instanceof Player && ((Player)p_174674_).isLocalPlayer();
                    double d1 = 0.0D;
                    if (flag) {
                        d1 = (double)p_174674_.getYRot();
                    } else if (entity instanceof ItemFrame) {
                        d1 = this.getFrameRotation((ItemFrame)entity);
                    } else if (entity instanceof ItemEntity) {
                        d1 = (double)(180.0F - ((ItemEntity)entity).getSpin(0.5F) / ((float)Math.PI * 2F) * 360.0F);
                    } else if (p_174674_ != null) {
                        d1 = (double)p_174674_.yBodyRot;
                    }

                    d1 = Mth.positiveModulo(d1 / 360.0D, 1.0D);
                    double d2 = this.getAngleTo(Vec3.atCenterOf(blockpos), entity) / (double)((float)Math.PI * 2F);
                    double d3;
                    if (flag) {
                        if (this.wobble.shouldUpdate(i)) {
                            this.wobble.update(i, 0.5D - (d1 - 0.25D));
                        }

                        d3 = d2 + this.wobble.rotation;
                    } else {
                        d3 = 0.5D - (d1 - 0.25D - d2);
                    }

                    return Mth.positiveModulo((float)d3, 1.0F);
                } else {
                    if (this.wobbleRandom.shouldUpdate(i)) {
                        this.wobbleRandom.update(i, Math.random());
                    }

                    double d0 = this.wobbleRandom.rotation + (double)((float)this.hash(p_174675_) / 2.14748365E9F);
                    return Mth.positiveModulo((float)d0, 1.0F);
                }
            }
        }

        private int hash(int p_174670_) {
            return p_174670_ * 1327217883;
        }


        @Nullable
        private BlockPos getLodestonePosition(Level p_117916_, CompoundTag compoundTag) {
            boolean flag = compoundTag.contains("HBLodestonePos");
            boolean flag1 = compoundTag.contains("HBLodestoneDimension");
            if (flag && flag1) {
                Optional<ResourceKey<Level>> optional = PlayerCompassItem.getLodestoneDimension(compoundTag);
                if (optional.isPresent() && p_117916_.dimension() == optional.get()) {
                    return NbtUtils.readBlockPos(compoundTag.getCompound("HBLodestonePos"));
                }
            }

            return null;
        }

        private double getFrameRotation(ItemFrame p_117914_) {
            Direction direction = p_117914_.getDirection();
            int i = direction.getAxis().isVertical() ? 90 * direction.getAxisDirection().getStep() : 0;
            return (double)Mth.wrapDegrees(180 + direction.get2DDataValue() * 90 + p_117914_.getRotation() * 45 + i);
        }

        private double getAngleTo(Vec3 p_117919_, Entity p_117920_) {
            return Math.atan2(p_117919_.z() - p_117920_.getZ(), p_117919_.x() - p_117920_.getX());
        }
    });
}

    @OnlyIn(Dist.CLIENT)
    static class CompassWobble {
        double rotation;
        private double deltaRotation;
        private long lastUpdateTick;

        boolean shouldUpdate(long pGameTime) {
            return this.lastUpdateTick != pGameTime;
        }

        void update(long pGameTime, double pWobbleAmount) {
            this.lastUpdateTick = pGameTime;
            double d0 = pWobbleAmount - this.rotation;
            d0 = Mth.positiveModulo(d0 + 0.5D, 1.0D) - 0.5D;
            this.deltaRotation += d0 * 0.1D;
            this.deltaRotation *= 0.8D;
            this.rotation = Mth.positiveModulo(this.rotation + this.deltaRotation, 1.0D);
        }
    }
}
