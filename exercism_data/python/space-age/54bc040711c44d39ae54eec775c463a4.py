'''space_age.py
	created 11 Oct 2014
	by @jestuber '''

EARTH_PERIOD = 31557600.0 # in seconds
PLANET_PERIODS = {'mercury': 0.2408467,
				'venus': 0.61519726,
				'earth': 1.0,
				'mars': 1.8808158,
				'jupiter': 11.862615,
				'saturn': 29.447498,
				'uranus': 84.016846,
				'neptune': 164.79132 }

class SpaceAge(object):
	"""given an age in seconds, calculates how old someone is in terms of a given planet's solar years."""
	def __init__(self, sec):
		super(SpaceAge, self).__init__()
		self.seconds = sec
		for planet in PLANET_PERIODS:
			self._add_method(planet)

	def _add_method(self,planet):
		func = lambda: self._on_planet(planet)
		func.__name__ = "on_" + planet
		self.__setattr__(func.__name__, func)

	def _on_planet(self,planet):
		return self._format_2dec(self.seconds / EARTH_PERIOD / PLANET_PERIODS[planet])

	def _format_2dec(self,num):
		return float("{0:.2f}".format(num))
