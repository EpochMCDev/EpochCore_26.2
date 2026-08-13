package org.dreeam.leaf.misc;

import io.papermc.paper.event.server.ServerResourcesReloadedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.dreeam.leaf.config.modules.gameplay.EnchantmentRestrictions;

public class EnchantmentRestrictionListener implements Listener {

    private static final NamespacedKey ENCHANTING_TABLE_RECIPE = NamespacedKey.minecraft("enchanting_table");

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onFishing(final PlayerFishEvent event) {
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) {
            return;
        }
        if (!(event.getCaught() instanceof Item item)) {
            return;
        }
        if (shouldRemoveBook(item.getItemStack(), true)) {
            event.setCancelled(true); // Prevent the whole catch from being spawned
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onVillagerAcquireTrade(final VillagerAcquireTradeEvent event) {
        if (shouldRemoveBook(event.getRecipe().getResult(), false)) {
            event.setCancelled(true); // Prevent the trade from being added
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onLootGenerate(final LootGenerateEvent event) {
        event.getLoot().removeIf(item -> item != null && shouldRemoveBook(item, false));
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onServerLoad(final ServerLoadEvent event) {
        if (event.getType() == ServerLoadEvent.LoadType.STARTUP) {
            removeEnchantingTableRecipe();
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onServerResourcesReloaded(final ServerResourcesReloadedEvent event) {
        removeEnchantingTableRecipe(); // Recipes are reloaded from data packs, re-apply the removal
    }

    private static void removeEnchantingTableRecipe() {
        if (EnchantmentRestrictions.disableEnchantingTableRecipe) {
            Bukkit.removeRecipe(ENCHANTING_TABLE_RECIPE, true); // Remove the crafting recipe and sync clients
        }
    }

    private static boolean shouldRemoveBook(final ItemStack item, final boolean fromFishing) {
        if (item.getType() != Material.ENCHANTED_BOOK) {
            return false;
        }
        if (fromFishing && EnchantmentRestrictions.disableFishingEnchantedBooks) {
            return true;
        }
        final boolean removeMending = EnchantmentRestrictions.disableMendingBooks;
        final boolean removeInfinity = EnchantmentRestrictions.disableInfinityBooks;
        if (!removeMending && !removeInfinity) {
            return false;
        }
        final EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        if (meta == null) {
            return false;
        }
        for (final Enchantment enchantment : meta.getStoredEnchants().keySet()) {
            if ((removeMending && enchantment.getKey().equals(Enchantment.MENDING.getKey())) || (removeInfinity && enchantment.getKey().equals(Enchantment.INFINITY.getKey()))) {
                return true;
            }
        }
        return false;
    }
}
