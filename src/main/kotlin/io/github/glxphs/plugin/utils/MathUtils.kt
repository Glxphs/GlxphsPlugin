package io.github.glxphs.plugin.utils

object MathUtils {
    fun scaleValue(value: Double, valueMin: Double, valueMax: Double, rangeMin: Double, rangeMax: Double): Double {
        val valueRange = valueMax - valueMin
        val range = rangeMax - rangeMin
        return ((value - valueMin) / valueRange) * range + rangeMin
    }
}