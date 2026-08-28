package org.dreeam.leaf.epoch;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Server;

/** Epoch Engine capability bridge for plugin-owned custom blocks, items, and effects. */
public final class EpochEngineAPI {

    private EpochEngineAPI() {
    }

    public static boolean isAvailable() {
        return Bukkit.getServer() instanceof EpochEngineBridge;
    }

    public static void configure(Collection<EpochEngineDefinition> definitions) {
        bridge().configureEpochEngines(List.copyOf(definitions));
    }

    public static void clear() {
        configure(List.of());
    }

    /** Registers one custom recipe. Existing Epoch Engine recipes with the same id are replaced. */
    public static void registerRecipe(EpochEngineRecipe recipe) {
        Objects.requireNonNull(recipe, "recipe");
        bridge().registerEpochEngineRecipe(recipe);
    }

    /** Registers multiple custom recipes without replacing recipes whose ids are not in the batch. */
    public static void registerRecipes(Collection<EpochEngineRecipe> recipes) {
        bridge().registerEpochEngineRecipes(List.copyOf(recipes));
    }

    /** Removes all previously configured Epoch Engine recipes and registers the supplied snapshot. */
    public static void configureRecipes(Collection<EpochEngineRecipe> recipes) {
        bridge().configureEpochEngineRecipes(List.copyOf(recipes));
    }

    /** Removes the Epoch Engine recipe registered under {@code recipeId}, if one exists. */
    public static void removeRecipe(String recipeId) {
        bridge().removeEpochEngineRecipe(Objects.requireNonNull(recipeId, "recipeId"));
    }

    /** Removes every Epoch Engine recipe whose id is present in {@code recipeIds}. */
    public static void removeRecipes(Collection<String> recipeIds) {
        bridge().removeEpochEngineRecipes(List.copyOf(recipeIds));
    }

    /** Removes all Epoch Engine recipes registered through this API. Vanilla recipes are left untouched. */
    public static void clearRecipes() {
        bridge().clearEpochEngineRecipes();
    }

    private static EpochEngineBridge bridge() {
        Server server = Bukkit.getServer();
        if (!(server instanceof EpochEngineBridge bridge)) {
            throw new UnsupportedOperationException("This server does not provide the Epoch Engine bridge");
        }
        return bridge;
    }
}
