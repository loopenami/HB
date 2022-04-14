package com.loopenami.hbmod.hb.client.keybinds;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;

public class KeyBindings {

    public static final String KEY_CATEGORIES_HB = "key.categories.hbm";
    public static final String KEY_TRAVERSE_TOGGLE = "key.traverseToggle";

    public static KeyMapping traverseToggleKeyMapping;

    public static void init() {
        traverseToggleKeyMapping = new KeyMapping(KEY_TRAVERSE_TOGGLE, KeyConflictContext.IN_GAME, InputConstants.getKey("key.keyboard.comma"), KEY_CATEGORIES_HB);
        ClientRegistry.registerKeyBinding(traverseToggleKeyMapping);
    }
}
