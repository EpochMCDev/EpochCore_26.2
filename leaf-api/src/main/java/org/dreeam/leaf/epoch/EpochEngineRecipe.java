package org.dreeam.leaf.epoch;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Immutable custom recipe supplied by a plugin to the Epoch Engine bridge.
 *
 * <p>A recipe has a unique namespaced {@code id}. The {@code shape} uses the
 * same compact syntax as a Bukkit shaped recipe, such as {@code ["AA"]} for a
 * horizontal two-slot recipe or {@code ["A", "A"]} for a vertical two-slot
 * recipe. Every non-space symbol must have an entry in {@code ingredients}.</p>
 *
 * <p>Both {@code output} and every ingredient item are resolved by the server
 * against its own item registry. This supports Epoch Engine custom items that
 * have no Bukkit {@code Material}.</p>
 *
 * @param id unique namespaced recipe id
 * @param type recipe kind; only {@link EpochEngineRecipeType#ITEM} is currently executed
 * @param output item produced by the recipe
 * @param shape shaped recipe layout
 * @param ingredients symbol to item mapping used by {@code shape}
 */
public record EpochEngineRecipe(
    String id,
    EpochEngineRecipeType type,
    EpochEngineRecipeOutput output,
    List<String> shape,
    Map<Character, EpochEngineRecipeIngredient> ingredients
) {

    public EpochEngineRecipe {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Epoch Engine recipe id cannot be blank");
        }
        type = Objects.requireNonNull(type, "Epoch Engine recipe type cannot be null");
        output = Objects.requireNonNull(output, "Epoch Engine recipe output cannot be null");
        if (shape == null || shape.isEmpty()) {
            throw new IllegalArgumentException("Epoch Engine recipe shape cannot be empty");
        }
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("Epoch Engine recipe ingredients cannot be empty");
        }

        List<String> validatedShape = List.copyOf(shape);
        if (validatedShape.size() > 3) {
            throw new IllegalArgumentException("Epoch Engine recipe shape cannot have more than 3 rows");
        }
        int width = -1;
        for (String row : validatedShape) {
            if (row == null || row.isEmpty()) {
                throw new IllegalArgumentException("Epoch Engine recipe shape rows cannot be blank");
            }
            if (row.length() > 3) {
                throw new IllegalArgumentException("Epoch Engine recipe shape rows cannot be wider than 3 columns");
            }
            if (width == -1) {
                width = row.length();
            } else if (row.length() != width) {
                throw new IllegalArgumentException("Epoch Engine recipe shape rows must have the same length");
            }
        }

        Map<Character, EpochEngineRecipeIngredient> validatedIngredients = Map.copyOf(ingredients);
        for (Map.Entry<Character, EpochEngineRecipeIngredient> entry : validatedIngredients.entrySet()) {
            if (entry.getKey() == null || entry.getKey() == ' ') {
                throw new IllegalArgumentException("Epoch Engine recipe ingredient symbols cannot be null or whitespace");
            }
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("Epoch Engine recipe ingredient value cannot be null for symbol '" + entry.getKey() + "'");
            }
        }
        for (String row : validatedShape) {
            for (int i = 0; i < row.length(); i++) {
                char symbol = row.charAt(i);
                if (symbol != ' ' && !validatedIngredients.containsKey(symbol)) {
                    throw new IllegalArgumentException("Epoch Engine recipe shape references undefined symbol '" + symbol + "'");
                }
            }
        }

        shape = validatedShape;
        ingredients = validatedIngredients;
    }

    /**
     * Creates a shaped item recipe with a single ingredient symbol.
     *
     * @param id unique namespaced recipe id
     * @param outputItem namespaced output item id
     * @param outputCount output stack size
     * @param shape shaped recipe layout
     * @param symbol ingredient symbol used in {@code shape}
     * @param inputItem namespaced input item id
     * @return a ready to register item recipe
     */
    public static EpochEngineRecipe item(
        final String id,
        final String outputItem,
        final int outputCount,
        final List<String> shape,
        final char symbol,
        final String inputItem
    ) {
        return new EpochEngineRecipe(
            id,
            EpochEngineRecipeType.ITEM,
            new EpochEngineRecipeOutput(outputItem, outputCount),
            shape,
            Map.of(symbol, new EpochEngineRecipeIngredient(inputItem))
        );
    }
}
