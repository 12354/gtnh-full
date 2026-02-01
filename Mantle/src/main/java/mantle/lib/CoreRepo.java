package mantle.lib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Mantle object repository
 *
 * Storage area for objects accessible throughout Mantle, such as loggers
 *
 * @author Sunstrike <sun@sunstrike.io>
 */
public final class CoreRepo {

    public static final String modId = "GRADLETOKEN_MODID";
    public static final String modName = "GRADLETOKEN_MODNAME";
    public static final String modVersion = "GRADLETOKEN_VERSION";

    public static final Logger logger = LogManager.getLogger(modName);

}
