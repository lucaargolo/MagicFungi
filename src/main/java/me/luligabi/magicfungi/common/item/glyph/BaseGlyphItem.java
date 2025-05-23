package me.luligabi.magicfungi.common.item.glyph;

import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class BaseGlyphItem extends Item implements GlyphExecutor {

    protected SoundEvent soundEvent;
    protected MushroomType mushroomType;
    protected BlockPos blockPos;
    protected ActionType actionType;

    public BaseGlyphItem (Settings settings) {
        super(settings);
        setMushroomType(MushroomType.INCOGNITA);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        blockPos = context.getBlockPos();
        World world = context.getWorld();
        PlayerEntity user = context.getPlayer();
        if (!world.isClient) {
            executeBlockGlyph(user, context.getStack());
        }
        return ActionResult.CONSUME;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        World world = user.getEntityWorld();
        if (!world.isClient) {
            executeEntityGlyph(user, stack, entity);
        }
        return ActionResult.CONSUME;
    }

    protected void executeGlyph(PlayerEntity playerEntity, ItemStack itemStack) {
        itemStack.decrement(1);
        playerEntity.getEntityWorld().playSound(null,
                playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(),
                soundEvent, SoundCategory.NEUTRAL, 1F, 1F);
        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
    }
    
    public MushroomType getMushroomType() {
        return mushroomType;
    }

    protected void setMushroomType(MushroomType mushroomType) {
        this.mushroomType = mushroomType;
    }

    protected void setSound(SoundEvent soundEvent) {
        this.soundEvent = soundEvent;
    }

    public void setActionType(ActionType actionType) { this.actionType = actionType; }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("tooltip.magicfungi.spell_info.1")
                    .formatted(MushroomType.getDarkColor(getMushroomType()), Formatting.BOLD)
                .append(new TranslatableText("tooltip.magicfungi.spell_info.2", mushroomType.getFancyName(), mushroomType.getStatsName())
                        .formatted(MushroomType.getLightColor(getMushroomType()))));
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("tooltip.magicfungi.spell_info.5")
                    .formatted(MushroomType.getDarkColor(getMushroomType()), Formatting.BOLD)
                    .append(actionType.getTranslatableText()
                            .formatted(MushroomType.getLightColor(getMushroomType()))));
        } else {
            tooltip.add(new TranslatableText("tooltip.magicfungi.extended_info").formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }
}