package org.dreeam.leaf.epochcraft;

import java.util.Collection;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Server;

/** Leaf capability bridge used by plugins to register custom items and blocks. */
public final class EpochcraftApi {

    private EpochcraftApi() {
    }

    public static boolean isAvailable() {
        return Bukkit.getServer() instanceof EpochcraftBridge;
    }

    public static void configure(Collection<EpochcraftDefinition> definitions) {
        Server server = Bukkit.getServer();
        if (!(server instanceof EpochcraftBridge bridge)) {
            throw new UnsupportedOperationException("This server does not provide the Epochcraft bridge");
        }
        bridge.configureEpochcraft(List.copyOf(definitions));
    }

    public static void clear() {
        configure(List.of());
    }
}
