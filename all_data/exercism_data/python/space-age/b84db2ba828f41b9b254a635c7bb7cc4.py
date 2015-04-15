from __future__ import division

class SpaceAge():
	def __init__(self,seconds):
		self.seconds = seconds
		self.earth_year = 31557600
		self.mercury_year = 0.2408467*self.earth_year
		self.venus_year = 0.61519726*self.earth_year
		self.mars_year = 1.8808158*self.earth_year
		self.jupiter_year = 11.862615*self.earth_year
		self.saturn_year = 29.447498*self.earth_year
		self.uranus_year = 84.016846*self.earth_year
		self.neptune_year = 164.79132*self.earth_year

	def on_earth(self):
		return round(self.seconds/self.earth_year,2)

	def on_mercury(self):
		return round(self.seconds/self.mercury_year,2)

	def on_venus(self):
		return round(self.seconds/self.venus_year,2)

	def on_mars(self):
		return round(self.seconds/self.mars_year,2)

	def on_jupiter(self):
		return round(self.seconds/self.jupiter_year,2)

	def on_saturn(self):
		return round(self.seconds/self.saturn_year,2)

	def on_uranus(self):
		return round(self.seconds/self.uranus_year,2)

	def on_neptune(self):
		return round(self.on_earth()/164.7132,2)
