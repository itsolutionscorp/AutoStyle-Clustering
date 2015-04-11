class SpaceAge(object):
	def __init__(self, seconds):
		self.seconds = seconds

# Generate methods into SpaceAge class
def _add_planet(name, period):
	setattr(SpaceAge, 'on_'+name, lambda self: round(self.seconds / 31557600.0 / period, 2))
for name, period in {
	'mercury': 0.2408467,
	'venus':   0.61519726,
	'earth':   1.0,
	'mars':    1.8808158,
	'jupiter': 11.862615,
	'saturn':  29.447498,
	'uranus':  84.016846,
	'neptune': 164.79132,
	'pluto':   247.68,    # feeling rebellious
}.iteritems():
	_add_planet(name, period)
