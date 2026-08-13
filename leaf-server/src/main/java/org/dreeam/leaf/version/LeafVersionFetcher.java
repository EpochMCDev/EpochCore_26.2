package org.dreeam.leaf.version;

import net.kyori.adventure.text.Component;
import org.galemc.gale.version.AbstractPaperVersionFetcher;

public class LeafVersionFetcher extends AbstractPaperVersionFetcher {

    public LeafVersionFetcher() {
        // Leaf - Version checking is disabled, no endpoints or repo are configured
        super("", "", "", "", "", ApiType.BIBLIOTHEK);
    }

    @Override
    public Component getVersionMessage() {
        return Component.empty(); // Leaf - Version checking is disabled
    }
}
