package me.luligabi.magicfungi.common.screenhandler;

import me.luligabi.magicfungi.common.MagicFungi;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ScreenHandlingRegistry {

    public static void init() {
        GLYPH_CARVING_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MagicFungi.MOD_ID, "glyph_carving_workbench"), GlyphCarvingScreenHandler::new);

        SPELL_DISCOVERY_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MagicFungi.MOD_ID, "spell_discovery_workbench"), SpellDiscoveryScreenHandler::new);
    }

    public static ScreenHandlerType<GlyphCarvingScreenHandler> GLYPH_CARVING_SCREEN_HANDLER;

    public static ScreenHandlerType<SpellDiscoveryScreenHandler> SPELL_DISCOVERY_SCREEN_HANDLER;
}