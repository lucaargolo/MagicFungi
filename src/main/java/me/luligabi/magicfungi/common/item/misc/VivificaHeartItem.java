package me.luligabi.magicfungi.common.item.misc;

import me.luligabi.magicfungi.common.misc.gamerule.GameRuleRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VivificaHeartItem extends Item {

    public VivificaHeartItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient()) return TypedActionResult.pass(user.getStackInHand(hand));
        if(world.getGameRules().getBoolean(GameRuleRegistry.DO_MORBUS_SPREADING)) {
            world.getGameRules().get(GameRuleRegistry.DO_MORBUS_SPREADING).set(false, world.getServer());
            user.getStackInHand(hand).decrement(1);
            user.getEntityWorld().playSound(null,
                    user.getX(), user.getY(), user.getZ(),
                    SoundEvents.BLOCK_MOSS_BREAK, SoundCategory.NEUTRAL, 1F, 1F);
            user.sendMessage(new TranslatableText("message.magicfungi.heart_of_vivifica").formatted(Formatting.GREEN, Formatting.ITALIC), false);
            return TypedActionResult.consume(user.getStackInHand(hand));
        } else {
            user.sendMessage(new TranslatableText("message.magicfungi.fate_design").formatted(Formatting.GRAY, Formatting.ITALIC), false);
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("tooltip.magicfungi.heart_of_vivifica.1").formatted(Formatting.GREEN, Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip.magicfungi.heart_of_vivifica.2").formatted(Formatting.GREEN, Formatting.ITALIC));
    }

}