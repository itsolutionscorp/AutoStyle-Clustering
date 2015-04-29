#/usr/bin/env python
class SpaceAge:
	def __init__(self,secs):
		self.seconds = secs
	def on_earth(self): return float('%.2f' % (self.seconds / 31557600.0))
	def on_mercury(self): return float('%.2f' % ((self.seconds * (1.0 / 0.2408467)) / 31557600.0))
	def on_venus(self): return float('%.2f' % ((self.seconds * (1.0 / 0.61519726)) / 31557600.0))
	def on_mars(self): return float('%.2f' % ((self.seconds * (1.0 / 1.8808158)) / 31557600.0))
	def on_jupiter(self): return float('%.2f' % ((self.seconds * (1.0 / 11.862615)) / 31557600.0))
	def on_saturn(self): return float('%.2f' % ((self.seconds * (1.0 / 29.447498)) / 31557600.0))
	def on_uranus(self): return float('%.2f' % ((self.seconds * (1.0 / 84.016846)) / 31557600.0))
	def on_neptune(self): return float('%.2f' % ((self.seconds * (1.0 / 164.79132)) / 31557600.0))
