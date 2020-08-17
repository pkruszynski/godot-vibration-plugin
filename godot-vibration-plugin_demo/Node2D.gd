extends Node2D


# Declare member variables here.
var vibration
var vibrating = false


# Called when the node enters the scene tree for the first time.
func _ready():
	# Getting screen height
	var y = OS.get_window_size().y
	# Scaling label font size depending on screen height
	var font_size = y / 1440 * 64
	# Setting labels font size
	$Label.get_font("font", "").size = font_size
	$Label2.get_font("font", "").size = font_size
	$Label3.get_font("font", "").size = font_size
	# Initializing "GodotVibrationPlugin" plugin
	if Engine.has_singleton("GodotVibrationPlugin"):
		vibration = Engine.get_singleton("GodotVibrationPlugin")
		# Initializing signals supported by "GodotVibrationPlugin" plugin
		vibration.connect("sdk_version", self, "on_sdk_version")
		vibration.connect("has_vibrator", self, "on_has_vibrator")
		vibration.connect("has_amplitude_control", self, "on_has_amplitude_control")
		# Invoking some of the existing "GodotVibrationPlugin" plugin methods
		vibration.sdkVersion()
		vibration.hasVibrator()
		vibration.hasAmplitudeControl()
		# Vibrating once for 100 ms on app start - method "vibrate" behaves the same as Godot's "vibrate_handheld" native method:
		# https://docs.godotengine.org/en/stable/classes/class_input.html#class-input-method-vibrate-handheld
		vibration.vibrate(100)


func on_sdk_version(ver: int):
	var message_text_1 = "SDK VERSION: " + str(ver)
	$Label.text = message_text_1


func on_has_vibrator(hv: bool):
	var message_text_2 = "HAS VIBRATOR: " + str(hv)
	$Label2.text = message_text_2


func on_has_amplitude_control(hac: bool):
	var message_text_3 = "HAS AMPLITUDE CONTROL: " + str(hac)
	$Label3.text = message_text_3


# Called when an InputEvent hasn't been consumed by _input or any GUI.
func _unhandled_input(event):
	if event is InputEventScreenTouch:
		if event.pressed:
			# Starting/stopping vibration pattern on screen touch
			if (vibrating):
				# Cancel an ongoing vibration
				vibration.vibrateCancel()
				vibrating = false
			else:
				# The "vibratePattern" method always requires 3 parameters (list of timings, list of amplitudes, repeat index)
				# in order to support the largest number of devices possible from just one method!
				# In case a phone does not support vibration amplitude the second parameter (list of amplitudes)
				# will be ignored and phone will vibrate with nominal strength.
				# First value of the first list parameter is the number of milliseconds to wait before turning the vibrator on.
				# Using -1 for any value of second list parameter (list of amplitudes) means that DEFAULT_AMPLITUDE
				# of the device will be used for that pattern timing - otherwise amplitude is number between 0 and 255.
				# Value of -1 for last parameter will make vibration pattern to reapate only once - otherwise vibration will
				# repeat itself until canceled, starting each consecutive repeat (after first that runs from the start)
				# from the supplied pattern timing index position.
				# On a phone with amplitude control below pattern will:
				# - wait 0 ms (as index 0 specifies)
				# - vibrate for 100 ms with amplitude of 85
				# - wat for 500 ms
				# - vibrate for 200 ms with amplitude of 170
				# - wat for 500 ms
				# - vibrate for 300 ms with amplitude of 255 (which is strongest possible)
				# - wat for 500 ms
				# - start repeating from index 5 which is to vibrate for 300 ms
				# - then pause for 500 ms (as index no. 6 specifies)
				# On phones without amplitude control phone will vibrate with the same pattern but strength of the vibration
				# will be the same for each pattern timing.
				vibration.vibratePattern([0, 100, 500, 200, 500, 300, 500], [0, 85, 0, 170, 0, 255, 0], 5)
				vibrating = true


# Called every frame. 'delta' is the elapsed time since the previous frame.
#func _process(delta):
#	pass
