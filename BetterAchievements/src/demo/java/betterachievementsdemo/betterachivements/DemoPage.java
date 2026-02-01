package betterachievementsdemo.betterachivements;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

import betterachievements.api.components.page.ICustomIcon;

public abstract class DemoPage extends AchievementPage implements ICustomIcon {

    public DemoPage(String name, Achievement... achievements) {
        super(name, achievements);
    }
}
