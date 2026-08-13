package org.dreeam.leaf.config.modules.gameplay;

import org.dreeam.leaf.config.ConfigModule;
import org.dreeam.leaf.config.ConfigCategory;

public class EnchantmentRestrictions extends ConfigModule {

    public String basePath() {
        return ConfigCategory.GAMEPLAY.basePath() + ".enchantment-restrictions";
    }

    public static boolean disableMendingBooks = true;
    public static boolean disableInfinityBooks = true;
    public static boolean disableFishingEnchantedBooks = true;
    public static boolean disableEnchantingTableRecipe = true;

    @Override
    public void onLoaded() {
        disableMendingBooks = globalConfig.getBoolean(basePath() + ".disable-mending-books", disableMendingBooks, globalConfig.pickStringRegionBased("""
                Prevent Mending enchanted books from being obtained from any source.""",
            """
                禁止从任何途径获取经验修补附魔书。"""));
        disableInfinityBooks = globalConfig.getBoolean(basePath() + ".disable-infinity-books", disableInfinityBooks, globalConfig.pickStringRegionBased("""
                Prevent Infinity enchanted books from being obtained from any source.""",
            """
                禁止从任何途径获取无限附魔书。"""));
        disableFishingEnchantedBooks = globalConfig.getBoolean(basePath() + ".disable-fishing-enchanted-books", disableFishingEnchantedBooks, globalConfig.pickStringRegionBased("""
                Prevent enchanted books from being obtained by fishing.""",
            """
                禁止通过钓鱼获得附魔书。"""));
        disableEnchantingTableRecipe = globalConfig.getBoolean(basePath() + ".disable-enchanting-table-recipe", disableEnchantingTableRecipe, globalConfig.pickStringRegionBased("""
                Remove the crafting recipe of the enchanting table.
                The block itself remains fully functional (e.g. when obtained from the creative inventory).""",
            """
                移除附魔台的合成配方。
                附魔台方块本身仍可正常使用（例如从创造模式物品栏获取）。"""));
    }
}
