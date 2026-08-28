package org.dreeam.leaf.epoch;

/**
 * Item produced by an {@link EpochEngineRecipe}.
 *
 * <p>The output id is resolved by the server runtime through its native item
 * registry, which is what allows Epoch Engine custom items to be crafted.</p>
 *
 * @param item namespaced item id produced by the recipe
 * @param count stack size produced by the recipe
 */
public record EpochEngineRecipeOutput(String item, int count) {

    public EpochEngineRecipeOutput {
        if (item == null || item.isBlank()) {
            throw new IllegalArgumentException("Epoch Engine recipe output item cannot be blank");
        }
        if (count < 1 || count > 99) {
            throw new IllegalArgumentException("Epoch Engine recipe output count must be between 1 and 99: " + count);
        }
    }
}
