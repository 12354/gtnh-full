package betterachievements.handler;

import net.minecraftforge.event.world.WorldEvent;

import betterachievements.registry.AchievementRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SaveHandler {

    public static String[] userSetIcons = new String[0];

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        ConfigHandler.saveUserSetIcons();
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        AchievementRegistry.instance().setUserSetIcons(userSetIcons);
    }
}
