package io.github.glxphs.plugin.items

import io.github.glxphs.plugin.GlxphsPlugin
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

open class CustomItem(
    val material: Material,
    val id: String,
    val name: String,
    val rarity: Rarity,
    val description: String? = null,
    val ability: Ability? = null,
    val meta: CustomItemMeta = CustomItemMeta()
) : ItemStack(material) {
    init {
        val tempMeta = itemMeta
        // Add the id to the item's nbt
        tempMeta.persistentDataContainer[GlxphsPlugin.itemIdKey, PersistentDataType.STRING] = id

        tempMeta.displayName(
            Component.text(name)
                .decoration(TextDecoration.ITALIC, false)
                .color(rarity.getDisplayColor())
        )

        val lore = mutableListOf<Component>()

        if (description != null) {
            lore.add(
                Component.text(description)
                    .color(NamedTextColor.DARK_GRAY)
                    .decoration(TextDecoration.ITALIC, false)
            )
            lore.add(
                Component.empty()
                    .decoration(TextDecoration.ITALIC, false)
            )
        }

        for (attribute in meta.attributes) {
            lore.add(
                Component.text(attribute.key.displayName)
                    .color(NamedTextColor.GRAY)
                    .append(
                        Component.text(": ")
                    )
                    .append(
                        Component.text(attribute.value)
                            .color(NamedTextColor.RED)
                    )
                    .decoration(TextDecoration.ITALIC, false)
            )
        }

        if (ability != null) {
            lore.add(
                Component.text(ability.name)
                    .color(NamedTextColor.GOLD)
                    .append(
                        Component.text(": ")
                    )
                    .append(
                        Component.text(ability.type.getDisplayName())
                            .decorate(TextDecoration.BOLD)
                    )
                    .decoration(TextDecoration.ITALIC, false)
            )
            lore.add(
                Component.text(ability.description)
                    .color(NamedTextColor.GRAY)
                    .decoration(TextDecoration.ITALIC, false)
            )
            lore.add(
                Component.empty()
                    .decoration(TextDecoration.ITALIC, false)
            )
        }

        lore.add(
            Component.text(rarity.getDisplayName())
                .append(Component.text(" Item"))
                .color(rarity.getDisplayColor())
                .decoration(TextDecoration.ITALIC, false)
        )

        // TODO: Remove item id from lore (debugging)
        lore.add(
            Component.text("Item ID: $id")
                .color(NamedTextColor.DARK_GRAY)
                .decoration(TextDecoration.ITALIC, false)
        )

        tempMeta.lore(lore)
        itemMeta = tempMeta
    }

    class CustomItemMeta() {
        enum class AttackSpeed {
            VERY_SLOW,
            SLOW,
            NORMAL,
            FAST,
            VERY_FAST
        }

        enum class Attribute(val displayName: String) {
            ATTACK_DAMAGE("Attack Damage"),
            ATTACK_SPEED("Attack Speed"),
            DURABILITY("Durability");
        }

        val attributes = mutableMapOf<Attribute, Double>()

        fun setAttribute(attribute: Attribute, value: Double) {
            attributes[attribute] = value
        }

        fun getAttribute(attribute: Attribute): Double? {
            if (!attributes.containsKey(attribute)) {
                return null
            }
            return attributes[attribute]
        }
    }
}