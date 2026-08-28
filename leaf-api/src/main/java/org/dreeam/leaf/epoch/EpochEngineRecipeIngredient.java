package org.dreeam.leaf.epoch;

/**
 * Single item input for an {@link EpochEngineRecipe}.
 *
 * <p>{@code item} is a Minecraft namespaced id and may refer to either a
 * vanilla item or an item registered by the Epoch Engine. The server runtime
 * resolves the id against its own item registry.</p>
 *
 * @param item namespaced item id consumed by the recipe
 */
public record EpochEngineRecipeIngredient(String item) {

    public EpochEngineRecipeIngredient {
        if (item == null || item.isBlank()) {
            throw new IllegalArgumentException("Epoch Engine recipe ingredient item cannot be blank");
        }
    }
}
