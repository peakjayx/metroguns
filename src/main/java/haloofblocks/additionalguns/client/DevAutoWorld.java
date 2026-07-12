package haloofblocks.additionalguns.client;

import haloofblocks.additionalguns.AdditionalGuns;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// Dev convenience: only active when -Dmetroguns.autoLoadWorld is set via
// the runClient task (see build.gradle), never in a real player's game.
@Mod.EventBusSubscriber(modid = AdditionalGuns.ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DevAutoWorld {
    private static boolean triggered = false;

    @SubscribeEvent
    public static void onGuiOpen(GuiOpenEvent event) {
        if (triggered || !(event.getGui() instanceof MainMenuScreen)) return;

        String world = System.getProperty("metroguns.autoLoadWorld");
        if (world == null || world.isEmpty()) return;

        triggered = true;
        Minecraft.getInstance().loadLevel(world);
    }
}
