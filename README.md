# Godot Android Vibration Plugin
Use Vibration in your Android Games built with Godot Game Engine!

## Supported Methods
* `sdkVersion` - checks device SDK version
* `hasVibrator` - checks if device has vibrator
* `hasAmplitudeControl` - checks if device supports amplitude control (vibration strengths)
* `vibrate` - vibrates device once for specified duration
* `vibratePattern` - vibrates device for a series of timing and amplitude pairs
* `vibrateCancel` - cancels ongoing vibration

## Plugin Build Instructions
### Build Prerequisites
* Android Studio
* Android SDK (platform version 29)
### Build Steps
* Clone this project and import it to Android Studio
* Download `AAR library for Android plugins` for your version of Godot from [Godot downloads page](https://godotengine.org/download)
* Place the downloaded `godot-lib.***.release.aar` file in `./godot-vibration-plugin/libs/` directory
* From your terminal run `./gradlew build` in the project directory
* Successfully built `debug` and `release` files will be placed in `/godot-vibration-plugin/build/outputs/aar/` directory
* Remember that Godot Android Plugins consist two files - the built `*.aar` file and respective `*.gdap` text file with content as described in [Creating Android plugins (Godot 3.2.2+)](https://docs.godotengine.org/en/stable/tutorials/plugins/android/android_plugin.html) - if you just want to use the plugin you can conveniently find both files on [plugin releases page](https://github.com/pkruszynski/godot-vibration-plugin/releases)

## Godot Project Example
### Example Info
Godot Vibration Plugin Example is provided in `godot-vibration-plugin_demo` directory and app built from it should:
* Vibrate the device on app start providing device has vibrator
* Display three labels showing device SDK version, info if device has vibrator and info if device supports amplitude control
* Start perpetual vibration pattern on screen touch (for explanation of pattern behavior see comment placed before invocation of `vibratePattern` method in [./godot-vibration-plugin_demo/Node2D.gd](https://github.com/pkruszynski/godot-vibration-plugin/blob/master/godot-vibration-plugin_demo/Node2D.gd) file)
* Stop started vibration pattern on subsequent screen touch
### Example Setup
* Import example project found in `./godot-vibration-plugin_demo/` directory to Godot
* From `Project` menu select `Install Android Build Template...` option
* Once template is installed place plugin files (`GodotVibrationPlugin.*.*.aar` and `GodotVibrationPlugin.*.*.gdap`) in `./godot-vibration-plugin_demo/android/plugins/` directory - Godot should automatically recognize the plugin (but gives no immediate feedback that it did)
* From `Project` menu select `Export...` option and in `Android` preset options:
    * Make sure that `Use Custom Build` option and `Godot Vibration Plugin` option (that only shows if plugin files are in correct directory) are ticked
    * Make sure that `Vibrate` is selected in `Permissions` section
* Export project to your Android device that supports vibration and test the demo app - your device should vibrate on screen tap
* In order to add vibration to your project you can follow similar steps to the ones above

## Plugin Usage
* See [./godot-vibration-plugin_demo/Node2D.gd](https://github.com/pkruszynski/godot-vibration-plugin/blob/master/godot-vibration-plugin_demo/Node2D.gd) file for example how to use Godot Vibration Plugin from Godot Script in your game

## Android Documentation Reference
* https://developer.android.com/reference/kotlin/android/os/Vibrator
* https://developer.android.com/reference/kotlin/android/os/VibrationEffect

## License
```
Licensed under a CC0 1.0 Universal (CC0 1.0) Public Domain Dedication
https://creativecommons.org/publicdomain/zero/1.0/
```
