package org.dreeam.leaf.epoch;

/**
 * Immutable custom content behavior supplied by a plugin to the Epoch Engine bridge.
 *
 * @param id registry identifier of the custom content
 * @param level content/effect level configured by the plugin
 * @param speedFactor movement-speed multiplier, where {@code 1.0} is vanilla speed
 */
public record EpochEngineDefinition(String id, int level, double speedFactor) {

    public EpochEngineDefinition {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Custom content id cannot be blank");
        }
        if (level < 1) {
            throw new IllegalArgumentException("Content level must be at least 1: " + level);
        }
        if (!Double.isFinite(speedFactor) || speedFactor < 1.0D) {
            throw new IllegalArgumentException("Content speed factor must be finite and at least 1.0: " + speedFactor);
        }
    }
}
