package org.dreeam.leaf.epocraft;

/**
 * Immutable custom item/block definition supplied by a plugin to the Epocraft Leaf bridge.
 *
 * @param id registry identifier of the custom item/block
 * @param type type of the custom item/block (e.g., "road", "tool", "armor")
 * @param properties custom properties map for type-specific behavior
 */
public record EpocraftDefinition(String id, String type, java.util.Map<String, Object> properties) {

    public EpocraftDefinition {
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
