package org.dreeam.leaf.epoch;

import java.util.Collection;

/** Internal server implementation behind {@link EpochEngineAPI}. */
public interface EpochEngineBridge {

    void configureEpochEngines(Collection<EpochEngineDefinition> definitions);

    void registerEpochEngineRecipe(EpochEngineRecipe recipe);

    void registerEpochEngineRecipes(Collection<EpochEngineRecipe> recipes);

    void configureEpochEngineRecipes(Collection<EpochEngineRecipe> recipes);

    void removeEpochEngineRecipe(String recipeId);

    void removeEpochEngineRecipes(Collection<String> recipeIds);

    void clearEpochEngineRecipes();
}
