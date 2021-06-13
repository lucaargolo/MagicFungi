package me.luligabi.magicfungi.common;

import me.luligabi.magicfungi.common.registry.BlockRegistry;
import me.luligabi.magicfungi.common.registry.GlyphRegistry;
import me.luligabi.magicfungi.common.registry.SpellRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MagicFungi implements ModInitializer {

    @Override
    public void onInitialize() {
        BlockRegistry.init();
        GlyphRegistry.init();
        SpellRegistry.init();
    }

    public static final String MOD_ID = "magicfungi";

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "item_group"))
            .icon(() -> new ItemStack(BlockRegistry.IMPETUS_MUSHROOM_BLOCK))
            .build();

}