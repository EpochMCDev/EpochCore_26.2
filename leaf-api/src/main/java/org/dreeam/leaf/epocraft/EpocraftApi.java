package org.dreeam.leaf.epocraft;

import java.util.Collection;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Server;

/** Leaf capability bridge used by plugins to register custom items and blocks. */
public final class EpocraftApi {

    private EpocraftApi() {
    }

    public static boolean isAvailable() {
        return Bukkit.getServer() instanceof EpocraftBridge;
    }

    public static void configure(Collection<EpocraftDefinition> definitions) {
        Server server = Bukkit.getServer();
        if (!(server instanceof EpocraftBridge bridge)) {
            throw new UnsupportedOperationException("This server does not provide the Epocraft bridge");
        }
        bridge.configureEpocraft(List.copyOf(definitions));
    }

    public static void clear() {
        configure(List.of());
    }
}
