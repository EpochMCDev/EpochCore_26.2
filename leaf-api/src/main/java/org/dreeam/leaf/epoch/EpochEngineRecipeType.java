package org.dreeam.leaf.epoch;

/**
 * Kind of custom recipe handled by the Epoch Engine bridge.
 *
 * <p>Only {@link #ITEM} is implemented by the current server runtime. Additional
 * kinds for custom blocks and effects can be added here without changing the
 * recipe API shape.</p>
 */
public enum EpochEngineRecipeType {
    /** Shaped crafting recipe producing an item registered in the server item registry. */
    ITEM
}
