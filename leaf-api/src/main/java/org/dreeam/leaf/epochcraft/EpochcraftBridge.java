package org.dreeam.leaf.epochcraft;

import java.util.Collection;

/** Internal server implementation behind {@link EpochcraftApi}. */
public interface EpochcraftBridge {

    void configureEpochcraft(Collection<EpochcraftDefinition> definitions);
}
