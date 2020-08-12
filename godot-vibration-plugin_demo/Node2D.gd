extends Node2D


# Declare member variables here.
var vibration


# Called when the node enters the scene tree for the first time.
func _ready():
	if Engine.has_singleton("GodotVibrationPlugin"):
		vibration = Engine.get_singleton("GodotVibrationPlugin")


# Called when an InputEvent hasn't been consumed by _input or any GUI.
func _unhandled_input(event):
	if event is InputEventScreenTouch:
		if event.pressed:
			var duration = 100 # Duration of the vibration in milliseconds
			vibration.vibrate(duration)


# Called every frame. 'delta' is the elapsed time since the previous frame.
#func _process(delta):
#	pass
