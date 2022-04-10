package com.loopenami.hbmod.item.custom;

import com.loopenami.hbmod.network.message.ClientboundCompassUpdate;
import com.loopenami.hbmod.network.message.PacketHandler;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;
import org.slf4j.Logger;

import java.util.Optional;

public class HBCompassItem extends Item {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static BlockPos playerPos = null;

    public HBCompassItem(Properties pProperties) {
        super(pProperties);
    }


    public static boolean isLodestoneCompass(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTag();
        return compoundtag != null && (compoundtag.contains("LodestoneDimension") || compoundtag.contains("LodestonePos"));
    }

    public boolean isFoil(ItemStack pStack) {
        return isLodestoneCompass(pStack) || super.isFoil(pStack);
    }

    public static Optional<ResourceKey<Level>> getLodestoneDimension(CompoundTag pCompoundTag) {
        return Level.RESOURCE_KEY_CODEC.parse(NbtOps.INSTANCE, pCompoundTag.get("LodestoneDimension")).result();
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
                if (compoundtag.contains("HBLodestoneTracked") && !compoundtag.getBoolean("HBLodestoneTracked")) {
                    return;
                }

                Optional<ResourceKey<Level>> optional = getLodestoneDimension(compoundtag);
                }
            }

        }

    public static void setPlayerPos(BlockPos playerPos) {
        HBCompassItem.playerPos = playerPos;
    }

    /**
     * Called when this item is used when targetting a Block
     */


    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        return super.use(pLevel, pPlayer, pUsedHand);
    }



    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
            Player player = pContext.getPlayer();
            ItemStack itemstack = pContext.getItemInHand();
        PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player),new ClientboundCompassUpdate(1));
                this.addLodestoneTags(level.dimension(), playerPos, itemstack.getOrCreateTag());

            return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private void addLodestoneTags(ResourceKey<Level> pLodestoneDimension, BlockPos pLodestonePos, CompoundTag pCompoundTag) {
        pCompoundTag.put("HBLodestonePos", NbtUtils.writeBlockPos(pLodestonePos));
        Level.RESOURCE_KEY_CODEC.encodeStart(NbtOps.INSTANCE, pLodestoneDimension).resultOrPartial(LOGGER::error).ifPresent((p_40731_) -> {
            pCompoundTag.put("HBLodestoneDimension", p_40731_);
        });
        pCompoundTag.putBoolean("HBLodestoneTracked", true);
    }

}
