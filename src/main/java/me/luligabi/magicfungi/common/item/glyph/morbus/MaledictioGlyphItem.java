package me.luligabi.magicfungi.common.item.glyph.morbus;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.item.glyph.BaseGlyphItem;
import me.luligabi.magicfungi.common.misc.tag.TagRegistry;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MaledictioGlyphItem extends BaseGlyphItem {

    public MaledictioGlyphItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.MORBUS);
        setSound(SoundEvents.ENTITY_WITHER_SHOOT);
        setActionType(ActionType.BLOCK);
    }

    @Override
    public void executeBlockGlyph(PlayerEntity playerEntity, ItemStack itemStack) {
        if(!MagicFungi.CONFIG.canUseMorbusCorruptionItems) return;
        World world = playerEntity.getEntityWorld();
        double radius = 6.5;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(playerEntity.getBlockX() + x, playerEntity.getBlockY() + y, playerEntity.getBlockZ() + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius && world.random.nextBoolean()) {
                        if(world.getBlockState(blockPos).isIn(TagRegistry.MORBUS_GRASS_SPREADABLE)) {
                            world.setBlockState(blockPos, BlockRegistry.HOST_GRASS_BLOCK.getDefaultState());
                        }
                        if(world.getBlockState(blockPos).isIn(TagRegistry.MORBUS_DIRT_SPREADABLE)) {
                            world.setBlockState(blockPos, BlockRegistry.HOST_DIRT.getDefaultState());
                        }
                        super.executeGlyph(playerEntity, itemStack);
                    }
                }
            }
        }
    }

    @Override
    public void executeEntityGlyph(PlayerEntity playerEntity, ItemStack itemStack, LivingEntity livingEntity) {}

}