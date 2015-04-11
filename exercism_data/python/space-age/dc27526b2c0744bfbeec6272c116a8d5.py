class SpaceAge:
	def __init__(self, secs):
		self.seconds = secs
		
	def on_earth(self):
		return int((self.seconds / 31557600.0)*100+0.5)/100.0

	def on_mercury(self):
		return int((self.seconds / 0.2408467 / 31557600.0)*100+0.5)/100.0

	def on_venus(self):
		return int((self.seconds / 0.61519726 / 31557600.0)*100+0.5)/100.0

	def on_mars(self):
		return int((self.seconds / 1.8808158 / 31557600.0)*100+0.5)/100.0

	def on_jupiter(self):
		return int((self.seconds / 11.862615 / 31557600.0)*100+0.5)/100.0

	def on_saturn(self):
		return int((self.seconds / 29.447498 / 31557600.0)*100+0.5)/100.0

	def on_uranus(self):
		return int((self.seconds / 84.016846 / 31557600.0)*100+0.5)/100.0

	def on_neptune(self):
		return int((self.seconds / 164.79132 / 31557600.0)*100+0.5)/100.0
