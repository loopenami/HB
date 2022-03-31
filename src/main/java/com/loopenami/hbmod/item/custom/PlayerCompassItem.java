package com.loopenami.hbmod.item.custom;

import com.loopenami.hbmod.block.ModBlocks;
import com.loopenami.hbmod.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;

import java.util.Optional;

public class PlayerCompassItem extends CompassItem {
    private static final Logger LOGGER = LogUtils.getLogger();

    public PlayerCompassItem(Properties p_40718_) {
        super(p_40718_);
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
        //Checks if it is client side obviously
        if (!pLevel.isClientSide) {
            //Checks to see if the compass is a lodestone compass
            if (isLodestoneCompass(pStack)) {
                //if it is, its tag is found, or it is given one
                CompoundTag compoundtag = pStack.getOrCreateTag();
                if (compoundtag.contains("LodestoneTracked") && !compoundtag.getBoolean("LodestoneTracked")) {
                    return;
                }

                Optional<ResourceKey<Level>> optional = getLodestoneDimension(compoundtag);
                if (optional.isPresent() && optional.get() == pLevel.dimension() && compoundtag.contains("LodestonePos")) {
                    BlockPos blockpos = NbtUtils.readBlockPos(compoundtag.getCompound("LodestonePos"));
                    if (!pLevel.isInWorldBounds(blockpos) || !((ServerLevel)pLevel).getPoiManager().existsAtPosition(PoiType.LODESTONE, blockpos)) {
                        compoundtag.remove("LodestonePos");
                    }
                }
            }

        }
    }

    /**
     * Called when this item is used when targetting a Block
     */
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        Block hbLodestone = ModBlocks.HB_LODESTONE.get();
        if (!level.getBlockState(blockpos).is(hbLodestone)) {
            return super.useOn(pContext);
        } else {
            level.playSound((Player)null, blockpos, SoundEvents.LODESTONE_COMPASS_LOCK, SoundSource.PLAYERS, 1.0F, 1.0F);
            Player player = pContext.getPlayer();
            ItemStack itemstack = pContext.getItemInHand();
            boolean flag = !player.getAbilities().instabuild && itemstack.getCount() == 1;
            if (flag) {
                this.addLodestoneTags(level.dimension(), blockpos, itemstack.getOrCreateTag());
            } else {
                ItemStack itemstack1 = new ItemStack(ModItems.PLAYER_COMPASS.get(), 1);
                CompoundTag compoundtag = itemstack.hasTag() ? itemstack.getTag().copy() : new CompoundTag();
                itemstack1.setTag(compoundtag);
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.addLodestoneTags(level.dimension(), blockpos, compoundtag);
                if (!player.getInventory().add(itemstack1)) {
                    player.drop(itemstack1, false);
                }
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    private void addLodestoneTags(ResourceKey<Level> pLodestoneDimension, BlockPos pLodestonePos, CompoundTag pCompoundTag) {
        pCompoundTag.put("LodestonePos", NbtUtils.writeBlockPos(pLodestonePos));
        Level.RESOURCE_KEY_CODEC.encodeStart(NbtOps.INSTANCE, pLodestoneDimension).resultOrPartial(LOGGER::error).ifPresent((p_40731_) -> {
            pCompoundTag.put("LodestoneDimension", p_40731_);
        });
        pCompoundTag.putBoolean("LodestoneTracked", true);
    }

}
