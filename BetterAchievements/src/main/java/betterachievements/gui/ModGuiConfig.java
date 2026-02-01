package betterachievements.gui;

import net.minecraft.client.gui.GuiScreen;

import betterachievements.handler.ConfigHandler;
import betterachievements.reference.Reference;
import cpw.mods.fml.client.config.GuiConfig;

public class ModGuiConfig extends GuiConfig {

    public ModGuiConfig(GuiScreen guiScreen) {
        super(
                guiScreen,
                ConfigHandler.getConfigElements(),
                Reference.ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }
}
