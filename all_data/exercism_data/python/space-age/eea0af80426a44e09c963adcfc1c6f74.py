EARTH_SECONDS = 31557600.0

PLANET_SECONDS = {"earth": 1, "mercury": 0.2408467, "venus": 0.61519726, "mars": 1.8808158, "jupiter": 11.862615, "saturn": 29.447498, "uranus": 84.016846, "neptune": 164.79132, "pluto": 247.92065}

class SpaceAge(object):

	def __init__(self,seconds):
		self.seconds = seconds
		for planet in PLANET_SECONDS:
			self._set_method(planet)
	
	def on_planet(self, planet):
		return round(self.seconds/EARTH_SECONDS/PLANET_SECONDS[planet], 2)

	def _set_method(self, planet):
		func = lambda: self.on_planet(planet)
		self.__setattr__("on_" + planet, func)
