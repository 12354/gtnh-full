package betterachievements.proxy;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;

import betterachievements.handler.ConfigHandler;
import betterachievements.handler.GuiOpenHandler;
import betterachievements.handler.SaveHandler;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register(new GuiOpenHandler());
        MinecraftForge.EVENT_BUS.register(new SaveHandler());
    }

    @Override
    public void initConfig(File file) {
        ConfigHandler.init(file);
        FMLCommonHandler.instance().bus().register(new ConfigHandler());
    }
}
