from enum import Enum

class Planet(Enum):
	mercury = 7600543
	venus   = 19414149
	earth   = 31557600
	mars    = 59354032
	jupiter = 374355659
	saturn  = 929292362
	uranus  = 2651370019
	neptune = 5200418560

class SpaceAge:
	def __init__(self, seconds):
		self.seconds = seconds

	def on_mercury(self):
		return self.on(Planet.mercury)

	def on_venus(self):
		return self.on(Planet.venus)

	def on_earth(self):
		return self.on(Planet.earth)

	def on_mars(self):
		return self.on(Planet.mars)

	def on_jupiter(self):
		return self.on(Planet.jupiter)

	def on_saturn(self):
		return self.on(Planet.saturn)

	def on_uranus(self):
		return self.on(Planet.uranus)

	def on_neptune(self):
		return self.on(Planet.neptune)

	def on(self, planet):
		return round(self.seconds / planet.value, 2)
