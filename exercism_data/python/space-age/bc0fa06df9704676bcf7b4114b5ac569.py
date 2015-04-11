planets = {
	'on_mercury':	0.2408467,
	'on_venus':		0.6151972,
	'on_earth':		1.0,
	'on_mars':		1.8808158,
	'on_jupiter':	11.862615,
	'on_saturn':	29.447498,
	'on_uranus':	84.016846,
	'on_neptune':	164.79132
}

def round(x):
	return int(100 * x + 0.50) / 100.0

class SpaceAge(object):

	def __init__(self, seconds):
		self.seconds = seconds

	def earth_years(self):
		return self.seconds / 31557600.0

	def __getattr__(self, name):
		def default():
			return round(self.earth_years() / planets[name])
		return default
