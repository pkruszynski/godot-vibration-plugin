package com.pkruszynski.godot.plugin.vibration

import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.SignalInfo


class GodotVibrationPlugin(godot: Godot) : GodotPlugin(godot) {

    private val vibrator =
        this.godot.applicationContext.getSystemService(VIBRATOR_SERVICE) as Vibrator

    private val sdkVersionSignal = SignalInfo("sdk_version", Int::class.javaObjectType)

    fun sdkVersion() {
        emitSignal(sdkVersionSignal.name, Build.VERSION.SDK_INT)
    }

    private val hasVibratorSignal = SignalInfo("has_vibrator", Boolean::class.javaObjectType)

    fun hasVibrator() {
        if (vibrator.hasVibrator()) {
            emitSignal(hasVibratorSignal.name, true)
        } else {
            emitSignal(hasVibratorSignal.name, false)
        }
    }

    private val hasAmplitudeControlSignal =
        SignalInfo("has_amplitude_control", Boolean::class.javaObjectType)

    fun hasAmplitudeControl() {
        if (Build.VERSION.SDK_INT >= 26) {
            if (vibrator.hasAmplitudeControl()) {
                emitSignal(hasAmplitudeControlSignal.name, true)
            } else {
                emitSignal(hasAmplitudeControlSignal.name, false)
            }
        } else {
            emitSignal(hasAmplitudeControlSignal.name, false)
        }
    }

    fun vibrate(duration: Int) {
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

    fun vibrateCancel() {
        if (vibrator.hasVibrator()) {
            vibrator.cancel()
        }
    }

    // LongArray data type needed for vibrator timings is not supported in Godot 3.2.2.
    // Using supported IntArray instead. More info about supported data types in the link below:
    // https://docs.godotengine.org/en/3.2/tutorials/plugins/android/android_plugin.html#godot-crashes-upon-load
    fun vibratePattern(timingsInt: IntArray, amplitudes: IntArray, repeat: Int) {
        // Converting timingsInt variable passed from Godot as IntArray type
        // to timingsLong variable LongArray type that can be used with vibrator
        val timingsList: MutableList<Long> = mutableListOf()
        for (timing in timingsInt) {
            timingsList.add(timing.toLong())
        }
        val timingsLong = timingsList.toLongArray()
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= 26) {
                if (vibrator.hasAmplitudeControl()) {
                    val effect =
                        VibrationEffect.createWaveform(timingsLong, amplitudes, repeat)
                    vibrator.vibrate(effect)
                } else {
                    val effect = VibrationEffect.createWaveform(timingsLong, repeat)
                    vibrator.vibrate(effect)
                }
            } else {
                vibrator.vibrate(timingsLong, repeat)
            }
        }
    }

    override fun getPluginName(): String {
        return "GodotVibrationPlugin"
    }

    override fun getPluginMethods(): List<String> {
        return listOf(
            "sdkVersion",
            "hasVibrator",
            "hasAmplitudeControl",
            "vibrate",
            "vibratePattern",
            "vibrateCancel"
        )
    }

    override fun getPluginSignals(): Set<SignalInfo> {
        return setOf(sdkVersionSignal, hasVibratorSignal, hasAmplitudeControlSignal)
    }
}
