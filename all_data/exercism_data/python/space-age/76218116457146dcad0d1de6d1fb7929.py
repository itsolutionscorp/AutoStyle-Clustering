ratios = {
	'mercury':	0.2408467,
	'venus':	0.6151972,
	'earth':	1.0,
	'mars':		1.8808158,
	'jupiter':	11.862615,
	'saturn':	29.447498,
	'uranus':	84.016846,
	'neptune':	164.79132
}

EARTH_YEAR_IN_SECONDS = 31557600.0

class SpaceAge(object):

	def __init__(self, seconds):
		self.seconds = seconds

	def __getattr__(self, name):
		return lambda: round(self.seconds / (EARTH_YEAR_IN_SECONDS * ratios[name[3:]]), 2)
