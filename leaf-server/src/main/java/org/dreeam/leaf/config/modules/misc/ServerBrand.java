package org.dreeam.leaf.config.modules.misc;

import org.dreeam.leaf.config.ConfigModule;
import org.dreeam.leaf.config.ConfigCategory;

public class ServerBrand extends ConfigModule {

    public String basePath() {
        return ConfigCategory.MISC.basePath() + ".rebrand";
    }

    public static String serverModName = "EpochCore"; // Leaf - Rebrand
    public static String serverGUIName = "EpochCore Console"; // Leaf - Rebrand

    @Override
    public void onLoaded() {
        serverModName = globalConfig.getString(basePath() + ".server-mod-name", serverModName);
        serverGUIName = globalConfig.getString(basePath() + ".server-gui-name", serverGUIName);
    }
}
