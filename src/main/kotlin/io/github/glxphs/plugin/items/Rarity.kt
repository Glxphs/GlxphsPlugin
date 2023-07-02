package io.github.glxphs.plugin.items

import net.kyori.adventure.text.format.TextColor
import java.util.*

enum class Rarity {
    COMMON,
    UNCOMMON,
    RARE,
    EPIC,
    LEGENDARY,
    MYTHIC;

    fun getDisplayName(): String {
        return name.lowercase(Locale.getDefault()).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    fun getDisplayColor(): TextColor {
        return when (this) {
            COMMON -> TextColor.color(0xFFFFFF)
            UNCOMMON -> TextColor.color(0x00FF00)
            RARE -> TextColor.color(0x007FFF)
            EPIC -> TextColor.color(0x7F00FF)
            LEGENDARY -> TextColor.color(0xFFA500)
            MYTHIC -> TextColor.color(0xFF00FF)
        }
    }
}