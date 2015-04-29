class SpaceAge(object):

	T_earth = 31557600 # seconds
	T_factor = { "mercury": 0.2408467,
				 "venus": 0.61519726,
				 "earth": 1.0,
				 "mars": 1.8808158,
				 "jupiter": 11.862615,
				 "saturn": 29.447498,
				 "uranus": 84.016846,
				 "neptune": 164.79132 }

	def __init__(self, sec):
		self.seconds = sec
		self.earth_age = float(sec) / self.T_earth

	def on_earth(self):
		return round(self.earth_age, 2)

	def __getattr__(self, attr):
		planet = attr.replace("on_","")
		return lambda: round(self.earth_age / self.T_factor[planet], 2)
