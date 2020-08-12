# Godot Android Vibration Plugin
Android devices vibration for Godot Game Engine.

## Prerequisites
* Android SDK (platform version 29)

### Plugin Build Setup
* Clone this project and import it to Android Studio
* Download `AAR library for Android plugins` for your version of Godot from [Godot downloads page](https://godotengine.org/download)
* Place the downloaded `godot-lib.***.release.aar` file in `./godot-vibration-plugin/libs/` directory
* From your terminal run `./gradlew build` in the project directory
* Successfully built `debug` and `release` files will be placed in `/godot-vibration-plugin/build/outputs/aar/` directory
* Remember that Godot Android Plugins consist two files - the built `*.aar` file needs to be accompanied by respective `*.gdap` text file as described in [Creating Android plugins (Godot 3.2.2+)](https://docs.godotengine.org/en/stable/tutorials/plugins/android/android_plugin.html)

### Godot Project Example Setup
* Import example project found in `./godot-vibration-plugin_demo/` directory to Godot
* From `Project` menu select `Install Android Build Template...` option
* Once template is installed place plugin files (`GodotVibrationPlugin.*.*.aar` and `GodotVibrationPlugin.*.*.gdap`) in `./godot-vibration-plugin_demo/android/plugins/` directory - Godot should automatically recognize the plugin (but gives no immediate feedback that it did)
* From `Project` menu select `Export...` option and in `Android` preset options:
    * Make sure that `Use Custom Build` option and `Godot Vibration Plugin` option (that only shows if plugin files are in correct directory) are ticked
    * Make sure that `Vibrate` is selected in `Permissions` section
* Export project to your Android device that supports vibration and test the demo app - your device should vibrate on screen tap
* In order to add vibration to your project you can follow similar steps to the ones above

### Plugin Usage
* See `./godot-vibration-plugin_demo/Node2D.gd` file for example how to use Godot Vibration Plugin from Godot Script in your game

### License
```
Licensed under a CC0 1.0 Universal (CC0 1.0) Public Domain Dedication
https://creativecommons.org/publicdomain/zero/1.0/
```
