package com.loopenami.hbmod.hb.client.keybinds;

import com.loopenami.hbmod.block.entity.server.Messages;
import com.loopenami.hbmod.hb.network.PacketTraversing;
import net.minecraftforge.client.event.InputEvent;

public class KeyInputHandler {
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if(KeyBindings.traverseToggleKeyMapping.consumeClick()) {
            Messages.sendToServer(new PacketTraversing());
        }
    }
}
