package io.github.glxphs.plugin.items.items

import io.github.glxphs.plugin.items.CustomItem
import io.github.glxphs.plugin.items.Rarity
import org.bukkit.Material

class BeginnersSword : CustomItem(
    Material.WOODEN_SWORD,
    "beginners_sword",
    "Beginner's Sword",
    Rarity.UNCOMMON,
    "A sword for beginners.",
    null,
    CustomItemMeta().apply {
        setAttribute(CustomItemMeta.Attribute.ATTACK_DAMAGE, 25.0)
    }
)