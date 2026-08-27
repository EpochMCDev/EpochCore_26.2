package org.dreeam.leaf.epochcraft;

/**
 * Immutable custom item/block definition supplied by a plugin to the Epochcraft Leaf bridge.
 *
 * @param id registry identifier of the custom item/block
 * @param type type of the custom item/block (e.g., "road", "tool", "armor")
 * @param properties custom properties map for type-specific behavior
 */
public record EpochcraftDefinition(String id, String type, java.util.Map<String, Object> properties) {

    public EpochcraftDefinition {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Item/block id cannot be blank");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be blank");
        }
        if (properties == null) {
            properties = java.util.Map.of();
        }
        properties = java.util.Map.copyOf(properties);
    }
}
