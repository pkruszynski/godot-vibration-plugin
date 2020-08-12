package com.pkruszynski.godot.plugin.vibration

import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin

class GodotVibrationPlugin(godot: Godot) : GodotPlugin(godot) {
    fun vibrate(duration: Int) {
        val vibrator = this.godot.applicationContext.getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        duration.toLong(),
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                vibrator.vibrate(duration.toLong())
            }
        }
    }

    override fun getPluginName(): String {
        return "GodotVibrationPlugin"
    }

    override fun getPluginMethods(): List<String> {
        return listOf(
            "vibrate"
        )
    }
}
