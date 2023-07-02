package io.github.glxphs.plugin.features

import io.github.glxphs.plugin.GlxphsPlugin
import org.bukkit.event.Listener

abstract class Feature : Listener {
    var isEnabled = false
        private set

    abstract fun onEnable()
    abstract fun onDisable()

    fun log(message: String) {
        GlxphsPlugin.instance.logger.info("[${javaClass.simpleName}] $message")
    }
}