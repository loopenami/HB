package com.loopenami.hbmod.world.entity.projectile;

import com.loopenami.hbmod.world.entity.ModEntityType;
import com.loopenami.hbmod.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import java.util.Random;

public class CrawliesArrow extends AbstractArrow {
    private final Item referenceItem;

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if(!pResult.getEntity().level.isClientSide) {
            super.onHitEntity(pResult);
            Random rand = new Random();
            int entityRaffle = rand.nextInt(3);
            ServerLevel world = ((ServerLevel) pResult.getEntity().level);
            int counter = 0;
            while(counter < 2) {
                EntityType entity = switch (entityRaffle) {
                    case 0 -> EntityType.CAVE_SPIDER;
                    case 1 -> EntityType.SPIDER;
                    case 2 -> EntityType.SILVERFISH;
                    default -> EntityType.SALMON;
                };
                entity.spawn(world, null, null, pResult.getEntity().blockPosition(), MobSpawnType.TRIGGERED, true, true);
                if (!(pResult.getEntity() instanceof Player) && pResult.getEntity() instanceof LivingEntity) {
                    pResult.getEntity().kill();
                }
                counter += 1;
            }
        }
    }

    public CrawliesArrow(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
        this.referenceItem = ModItems.CRAWLIES_ARROW.get();
    }

    public CrawliesArrow(LivingEntity shooter, Level level, Item referenceItem) {
        super(ModEntityType.CRAWLIES_ARROW.get(), shooter, level);
        this.referenceItem = referenceItem;
    }

    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }


}
