EARTHPERIOD = float(31557600)
MERCURYPERIOD = EARTHPERIOD * 0.2408467
VENUSPERIOD = EARTHPERIOD * 0.61519726
MARSPERIOD = EARTHPERIOD * 1.8808158
JUPITERPERIOD = EARTHPERIOD * 11.862615
SATURNPERIOD = EARTHPERIOD * 29.447498
URANUSPERIOD = EARTHPERIOD * 84.016846
NEPTUNEPERIOD = EARTHPERIOD * 164.79132

class SpaceAge:

	def __init__(self, seconds):
		self.seconds = seconds
	def on_earth(self):
		return round(self.seconds / EARTHPERIOD, 2)
	def on_mercury(self):
		return round(self.seconds / MERCURYPERIOD, 2)
	def on_venus(self):
		return round(self.seconds / VENUSPERIOD, 2)
	def on_mars(self):
		return round(self.seconds / MARSPERIOD, 2)
	def on_jupiter(self):
		return round(self.seconds / JUPITERPERIOD, 2)
	def on_saturn(self):
		return round(self.seconds / SATURNPERIOD, 2)
	def on_uranus(self):
		return round(self.seconds / URANUSPERIOD, 2)
	def on_neptune(self):
		return round(self.seconds / NEPTUNEPERIOD, 2)
