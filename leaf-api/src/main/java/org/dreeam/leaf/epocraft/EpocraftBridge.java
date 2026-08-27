package org.dreeam.leaf.epocraft;

import java.util.Collection;

/** Internal server implementation behind {@link EpocraftApi}. */
public interface EpocraftBridge {

    void configureEpocraft(Collection<EpocraftDefinition> definitions);
}
