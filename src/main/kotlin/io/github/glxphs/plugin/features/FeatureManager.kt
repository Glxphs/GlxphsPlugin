package io.github.glxphs.plugin.features

import io.github.glxphs.plugin.GlxphsPlugin
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList

class FeatureManager {
    private val features = mutableListOf<Feature>()

    init {
        features.add(InfoBar())
        features.add(Damage())
    }

    fun enableFeatures() {
        features.forEach { feature ->
            if (!feature.isEnabled) {
                feature.onEnable()
            }
            Bukkit.getPluginManager().registerEvents(feature, GlxphsPlugin.instance)
        }
    }

    fun disableFeatures() {
        features.forEach { feature ->
            if (feature.isEnabled) {
                feature.onDisable()
            }
            HandlerList.unregisterAll(feature)
        }
    }
}